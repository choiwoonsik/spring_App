package jpaBook.jpaShop.domain.item;

import jpaBook.jpaShop.controller.form.BookForm;
import jpaBook.jpaShop.domain.CommonBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {
	private String author;
	private String isbn;
}
