package jpaBook.jpaShop.repository.order.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpaBook.jpaShop.domain.Address;
import jpaBook.jpaShop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {

    @JsonIgnore
    private Long orderId;
    private String name;
    private LocalDateTime localDateTime;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime localDateTime, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.localDateTime = localDateTime;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
