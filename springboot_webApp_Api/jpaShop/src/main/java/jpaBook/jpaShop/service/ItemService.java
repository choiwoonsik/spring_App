package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.exception.NoSuchItemInRepository;
import jpaBook.jpaShop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	// 아이템 등록
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		itemRepository.change(itemId, name, price, stockQuantity);
	}

	// 아이템 조회
	public Item findItem(Long id) {
		return itemRepository.findOne(id);
	}

	// 모든 아이템 조회
	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}

	// 아이템 제거
	@Transactional
	public void removeItem(Item item) throws NoSuchItemInRepository {
		itemRepository.removeItem(item);
	}
}
