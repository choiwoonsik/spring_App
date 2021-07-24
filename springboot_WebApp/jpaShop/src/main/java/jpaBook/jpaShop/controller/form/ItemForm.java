package jpaBook.jpaShop.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public abstract class ItemForm {
	private Long id;

	@NotEmpty(message = "상품명을 입력해주세요.")
	private String name;

	@NotNull(message = "상품 가격을 입력해주세요.")
	@Range(min = 0, max = 1000000000, message = "입력할 수 없는 값입니다.")
	private int price;

	@NotNull(message = "상품 개수를 입력해주세요.")
	@Range(min = 0, max = 1000000000, message = "입력할 수 없는 값입니다.")
	private int stockQuantity;
}
