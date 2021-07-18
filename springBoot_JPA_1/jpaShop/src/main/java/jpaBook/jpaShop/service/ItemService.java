package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.exception.NoSuchItemInRepository;
import jpaBook.jpaShop.repository.ItemDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemDaoImpl itemDaoImpl;

	// 아이템 등록
	@Transactional
	public void saveItem(Item item) {
		itemDaoImpl.save(item);
	}

	// 아이템 조회
	public Item findItem(Long id) {
		return itemDaoImpl.findOne(id);
	}

	// 모든 아이템 조회
	public List<Item> findAllItems() {
		return itemDaoImpl.findAll();
	}

	// 아이템 제거
	@Transactional
	public void removeItem(Item item) throws NoSuchItemInRepository {
		itemDaoImpl.removeItem(item);
	}
}
