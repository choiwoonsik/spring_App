package jpaBook.jpaShop.api;

import jpaBook.jpaShop.domain.Address;
import jpaBook.jpaShop.domain.Order;
import jpaBook.jpaShop.domain.OrderItem;
import jpaBook.jpaShop.domain.OrderStatus;
import jpaBook.jpaShop.repository.OrderRepository;
import jpaBook.jpaShop.repository.OrderSearch;
import jpaBook.jpaShop.repository.order.query.OrderQueryDto;
import jpaBook.jpaShop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;

	/*
	Order -> OrderItem 내 Order에 JsonIgnore
	Order -> Delivery 내 Order에 JsonIgnore
	필요
	 */
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1() {
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for (Order order : all) {
			order.getMember().getName(); // LAZY 해제, 즉시로딩
			order.getDelivery().getAddress(); // LAZY 해제, 즉시로딩

			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.forEach(o -> o.getItem().getName()); // LAZY 해제, 즉시로딩
		}
		return all;
	}

	@GetMapping("/api/v2/orders")
	public List<OrderDto> ordersV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		List<OrderDto> all = orders.stream()
				.map(OrderDto::new)
				.collect(Collectors.toList());
		return all;
	}

	@GetMapping("/api/v3/orders")
	public List<OrderDto> orderV3() {
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDto> all = orders.stream()
				.map(OrderDto::new)
				.collect(Collectors.toList());
		return all;
	}
	/*
	select * from orders o
	join order_item oi on o.order_id = oi.order_id
	 */

	@GetMapping("/api/v3.1/orders")
	public List<OrderDto> orderV3_page(
			@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		// toOne 관계 객체를 먼저 FETCH JOIN으로 가져온다.
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		// 이후 toMany(컬렉션)객체를 가져온다.
		List<OrderDto> all = orders.stream()
				.map(OrderDto::new)
				.collect(Collectors.toList());
		return all;
	}

	@GetMapping("/api/v4/orders")
	public List<OrderQueryDto> orderV4() {
		return orderQueryRepository.findOrderQueryDtos();
	}


	@Data
	static class OrderDto {
		private Long orderId;
		private String name;
		private LocalDateTime localDateTime;
		private OrderStatus orderStatus;
		private Address address;
		private List<OrderItemDto> orderItems;

		public OrderDto(Order order) {
			orderId = order.getId();
			name = order.getMember().getName();
			localDateTime = order.getOrderDate();
			orderStatus = order.getStatus();
			address = order.getDelivery().getAddress();
			orderItems = order.getOrderItems().stream()
					.map(OrderItemDto::new)
					.collect(Collectors.toList());
		}
	}

	@Data
	static class OrderItemDto {
		private Long orderItemId;
		private String itemName;
		private int price;
		private int count;

		public OrderItemDto(OrderItem o) {
			orderItemId = o.getId();
			itemName = o.getItem().getName();
			price = o.getOrderPrice();
			count = o.getCount();
		}
	}

}
