package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.*;
import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.exception.NotEnoughStockException;
import jpaBook.jpaShop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	private Member createMember(String name) {
		Member member = new Member();
		member.setName(name);
		member.setAddress(new Address("seoul", "jongro", "13910"));
		em.persist(member);
		return member;
	}

	private Item createBook(String name, int price, int quantity) {
		Item book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(quantity);
		em.persist(book);
		return book;
	}

	@Test
	public void orderItem() throws Exception {

	    //given
		Member member = createMember("memberA");
		Item book = createBook("JPA", 10000, 10);

		int orderCount = 2;
		int remainStock = 10 - orderCount;

	    //when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		//then
		Order getOrder = orderRepository.findOne(orderId);
		Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품주문시 상태는 Order");
		Assertions.assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다");
		Assertions.assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 금액이 일치해야 한다");
		Assertions.assertEquals(remainStock, book.getStockQuantity(), "주문한 수량만큼 줄어야 한다");
	}

	@Test
	public void cancelItem() throws Exception {
	    //given
		Member member = createMember("memberA");
		Item book = createBook("jap", 10000, 3);

		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

	    //when
		orderService.cancelOrder(orderId);

	    //then
		Order getOrder = orderRepository.findOne(orderId);
		Assertions.assertEquals(getOrder.getStatus(), OrderStatus.CANCEL);
		Assertions.assertEquals(book.getStockQuantity(), 3);
	}

	@Test
	public void  orderItem_overStock() throws Exception {
	    //given
		Item book = createBook("JPA", 10000, 10);
		Member member = createMember("memberA");
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		delivery.setStatus(DeliveryStatus.READY);

	    //when

	    //then
		Assertions.assertThrows(NotEnoughStockException.class, () -> {
			orderService.order(member.getId(), book.getId(), 11);
		});
	}
}