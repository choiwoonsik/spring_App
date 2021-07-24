package jpaBook.jpaShop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MovieForm extends ItemForm {
	@NotEmpty(message = "디렉터명을 입력해주세요.")
	private String Director;
	@NotEmpty(message = "출연 배우명을 입력해주세요.")
	private String actor;
}
