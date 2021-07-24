package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.exception.NoSuchItemInRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ItemServiceTest {

	@Autowired
	ItemService itemService;

	@Test
	public void saveItem() throws Exception {
	    //given
		Item bookItem = new Book();
		bookItem.setName("bookA");
	    itemService.saveItem(bookItem);

	    //when
		Item book = itemService.findItem(bookItem.getId());

		//then
		Assertions.assertThat(bookItem).isSameAs(book);
		Assertions.assertThat(bookItem.getName()).isEqualTo(book.getName());
	}

	@Test
	public void removeItem() throws Exception {
	    //given
	    Item bookA = new Book();
	    itemService.saveItem(bookA);

	    //when
		itemService.removeItem(bookA);
		Item item = itemService.findItem(bookA.getId());

		//then
		Assertions.assertThat(item).isSameAs(null);
		org.junit.jupiter.api.Assertions.assertThrows(NoSuchItemInRepository.class, () -> {
			itemService.removeItem(bookA);
		});
	}
}