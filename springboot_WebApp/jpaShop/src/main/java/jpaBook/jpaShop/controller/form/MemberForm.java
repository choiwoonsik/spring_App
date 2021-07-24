package jpaBook.jpaShop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {

	@Size(max = 10, message = "10자 이상 입력할 수 없습니다.")
	@NotEmpty(message = "성함을 입력해주세요.")
	private String name;

	@Email(message = "올바른 형식의 이메일을 입력하세요.")
	@NotEmpty(message = "이메일을 입력해주세요.")
	private String email;

	@NotEmpty(message = "주소를 입력해주세요.")
	private String zipcode;
	private String streetAdr;
	private String detailAdr;
}
