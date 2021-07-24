package jpaBook.jpaShop.repository;

import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.item.ItemType;
import jpaBook.jpaShop.exception.NoSuchItemInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository("ItemDao")
@RequiredArgsConstructor
public class ItemDaoImpl implements ItemDao {

	private final EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
			// entity manager 에 의해 아직 등록되지 않은 경우 id가 없다 -> 새로 등록
			em.persist(item);
		} else {
			// 이미 있는 경우 업데이트
			em.merge(item);
		}
	}

	@Override
	public void change(Long id, String name, int price, int stockQuantity) {
		Item item = findOne(id);
		item.setName(name);
		item.setPrice(price);
		item.setStockQuantity(stockQuantity);
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}

	public void removeItem(Item item) throws NoSuchItemInRepository {
		if (em.find(Item.class, item.getId()) != null)
			em.remove(item);
		else throw new NoSuchItemInRepository("Don't have such item");
	}
}
