package jpaBook.jpaShop.domain.item;

import jpaBook.jpaShop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	private String name;

	private int price;

	private int stockQuantity;

	@Enumerated(EnumType.STRING)
	private ItemType itemType;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	//==비지니스 로직==//

	// stock 증가
	public void addStock(int stockQuantity) {
		this.stockQuantity += stockQuantity;
	}

	// stock 감소
	public void removeStock(int stockQuantity) {
		int remainStock = this.stockQuantity - stockQuantity;
		if (remainStock < 0 || stockQuantity <= 0) {
			throw new NotEnoughStockException("Need more stock");
		}
		this.stockQuantity = remainStock;
	}
}
