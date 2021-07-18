package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.Delivery;
import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.domain.Order;
import jpaBook.jpaShop.domain.OrderItem;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.repository.ItemDaoImpl;
import jpaBook.jpaShop.repository.MemberDaoImpl;
import jpaBook.jpaShop.repository.OrderDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderDaoImpl orderDaoImpl;
	private final MemberDaoImpl memberDaoImpl;
	private final ItemDaoImpl itemDaoImpl;

	/**
	 * 주문
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {

		// Entity 조회
		Member member = memberDaoImpl.findOne(memberId);
		Item item = itemDaoImpl.findOne(itemId);

		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		orderDaoImpl.save(order);

		return order.getId();
	}

	/**
	 * 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		// 주문 엔티티 조회
		Order order = orderDaoImpl.findOne(orderId);
		// 주문 취소
		order.cancel();
	}

	/**
	 * 검색 - 동적 쿼리 활용
	 */
//	public List<Order> findOrders(OrderSearch orderSearch) {
//		orderRepository
//	}
}
