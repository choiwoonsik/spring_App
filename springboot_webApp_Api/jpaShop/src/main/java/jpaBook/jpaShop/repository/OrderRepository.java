package jpaBook.jpaShop.repository;

import jpaBook.jpaShop.domain.Order;
import jpaBook.jpaShop.repository.order.simplequery.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("OrderDao")
@RequiredArgsConstructor
public class OrderRepository implements OrderDao {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	/*
	검색 기능을 통해 조회 (1) - String
	 */
	public List<Order> findAllByString(OrderSearch orderSearch) {

		// 해당 jpql은 상태와 이름이 둘다 있는 경우에만 가능하다 -> 만약 하나씩만 있는 경우에는? (동적 쿼리필요)
		String jpql = "select o From Order o join o.member m";
		boolean isFirstCondition = true;

		//주문 상태 검색
		if (orderSearch.getOrderStatus() != null) {
			if (isFirstCondition) {
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " o.status = :status";
		}

		//회원 이름 검색
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			if (isFirstCondition) {
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " m.name like :name";
		}

		TypedQuery<Order> query = em.createQuery(jpql, Order.class)
				.setMaxResults(1000); //최대 1000건

		if (orderSearch.getOrderStatus() != null) {
			query = query.setParameter("status", orderSearch.getOrderStatus());
		}
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			query = query.setParameter("name", orderSearch.getMemberName());
		}
		return query.getResultList();
	}

	/*
	검색 기능을 통해 조회 (2) - Criteria
	 */
	public List<Order> findAllByCriteria(OrderSearch orderSearch) {
		return null;
	}

	/*
	검색 기능을 통해 조회 (3) - QueryDSL
	 */
	public List<Order> findAll(OrderSearch orderSearch) {
		return null;
	}

	public List<Order> findAllWithMemberDelivery() {
		return em.createQuery(
				"select o from Order o" +
						" join fetch o.member m" +
						" join fetch o.delivery d", Order.class
		).getResultList();
	}
}
