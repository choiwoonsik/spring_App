package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ItemUpdateTest {

	@Autowired
	ItemService itemService;

	@Test
	public void updateTest() throws Exception {
	    //given
		Item book = new Book();
		book.setName("before name");
		itemService.saveItem(book);
		Book b = (Book) itemService.findItem(book.getId());

	    //when
		b.setName("new name");
	    
	    //then
		Assertions.assertEquals(itemService.findItem(book.getId()).getName(), b.getName());
		// JPA 변경 감지 == dirty check
	}
}