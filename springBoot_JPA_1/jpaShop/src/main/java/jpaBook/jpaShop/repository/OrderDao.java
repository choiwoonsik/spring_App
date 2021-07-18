package jpaBook.jpaShop.repository;

import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.domain.Order;

import java.util.List;

public interface OrderDao {
	/*
	주문 저장
	 */
	void save(Order order);

	/*
	주문 아이디로 검색 후 반환
	 */
	Order findOne(Long id);

	/*
	모든 주문 반환 (동적 쿼리를 위한 QueryDSL 사용)
	 */
	List<Order> findAll(OrderSearch orderSearch);
}
