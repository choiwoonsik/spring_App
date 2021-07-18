package jpaBook.jpaShop.repository;

import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.exception.NoSuchItemInRepository;

import java.util.List;

public interface ItemDao {
	/*
	Item 저장
	 */
	void save(Item item);

	/*
	Item id로 찾기
	 */
	Item findOne(Long id);

	/*
	모든 item 반환
	 */
	List<Item> findAll();

	/*
	item 제거
	 */
	void removeItem(Item item) throws NoSuchItemInRepository;
}
