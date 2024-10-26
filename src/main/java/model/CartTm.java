package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTm {
    private String cusName;
    private String itemCode;
    private String description;
    private Integer qty;
    private Double price;
    private Double total;

    public CartTm(String itemCode, String description, Integer qty, Double price, Double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }



}
