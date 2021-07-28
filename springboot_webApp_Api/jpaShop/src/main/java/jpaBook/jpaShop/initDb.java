package jpaBook.jpaShop;

import jpaBook.jpaShop.domain.*;
import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {

	private final InitService initService;

	@PostConstruct
	public void init() {
		initService.dbInit1();
		initService.dbInit2();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {
		private final EntityManager em;

		public void dbInit1() {
			Member member = new Member();
			createMember(member, "userA", "dnstlr2933@naver.com", "13928", "동편로", "000-000");
			em.persist(member);

			Book book1 = new Book();
			createBook(book1, "JAVA", 10000, 44);
			em.persist(book1);

			Book book2 = new Book();
			createBook(book2, "Algorithm", 23000, 55);
			em.persist(book2);

			OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 1);
			OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 2);

			Delivery delivery = createDelivery(member);
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
		}

		public void dbInit2() {
			Member member = new Member();
			createMember(member, "userB", "dnstlr2934@naver.com", "13929", "동편로9", "009-009");
			em.persist(member);

			Book book1 = new Book();
			createBook(book1, "SPRING", 14000, 12);
			em.persist(book1);

			Book book2 = new Book();
			createBook(book2, "JPA", 23900, 120);
			em.persist(book2);

			OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 1);
			OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 2);

			Delivery delivery = createDelivery(member);
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
		}

		private void createBook(Book book1, String name, int count, int stockN) {
			book1.setName(name);
			book1.setPrice(count);
			book1.setStockQuantity(stockN);
			book1.setItemType(ItemType.Book);
		}

		private void createMember(Member member, String name, String email, String zipcode, String street, String detail) {
			member.setName(name);
			member.setEmail(email);
			member.setAddress(new Address(zipcode, street, detail));
		}

		private Delivery createDelivery(Member member) {
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			return delivery;
		}
	}
}
