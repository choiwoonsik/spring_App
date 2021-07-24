package jpaBook.jpaShop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookForm extends ItemForm {
	@NotEmpty(message = "저자명을 입력해주세요.")
	private String author;
	@NotEmpty(message = "ISBN을 입력해주세요.")
	private String isbn;
}
