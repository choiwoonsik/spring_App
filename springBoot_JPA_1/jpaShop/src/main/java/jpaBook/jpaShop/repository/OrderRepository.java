package jpaBook.jpaShop.repository;

import jpaBook.jpaShop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	/*
	검색 기능을 통해 조회
	 */
//	public List<Order> findAll(OrderSearch orderSearch) {}
}
