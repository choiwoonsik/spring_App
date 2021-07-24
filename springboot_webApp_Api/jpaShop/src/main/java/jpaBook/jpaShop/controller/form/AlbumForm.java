package jpaBook.jpaShop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AlbumForm extends ItemForm {
	@NotEmpty (message = "가수명을 입력해주세요.")
	private String Artist;
	private String etc;
}
