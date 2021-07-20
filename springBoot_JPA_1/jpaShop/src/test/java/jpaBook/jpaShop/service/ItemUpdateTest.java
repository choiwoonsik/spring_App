package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@SpringBootTest
public class ItemUpdateTest {

	@Autowired
	EntityManager em;

	@Test
	public void updateTest() throws Exception {
	    //given
		Book book = em.find(Book.class, 1L);

	    //when
		book.setName("new name");
	    
	    //then
		// JPA 변경 감지 == dirty check
	}
}