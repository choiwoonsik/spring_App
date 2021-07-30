package jpaBook.jpaShop.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {
    private Long orderItemId;
    private String itemName;
    private int price;
    private int count;

    public OrderItemQueryDto(Long orderItemId, String itemName, int price, int count) {
        this.orderItemId = orderItemId;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }
}
