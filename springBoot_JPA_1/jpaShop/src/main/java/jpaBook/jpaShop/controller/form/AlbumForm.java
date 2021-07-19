package jpaBook.jpaShop.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumForm {
	private Long id;

	private String name;
	private int price;
	private int stockQuantity;

	private String Artist;
	private String etc;
}
