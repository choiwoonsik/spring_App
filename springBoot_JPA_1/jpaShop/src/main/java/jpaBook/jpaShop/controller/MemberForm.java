package jpaBook.jpaShop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {

	@Size(max = 5)
	@NotEmpty(message = "회원 이름은 필수입니다.")
	private String name;

	private String zipcode;
	private String streetAdr;
	private String detailAdr;
}
