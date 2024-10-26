package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderEntity {

    @Id
    private String id;
    private String date;
    private String time;
    private String name;
    private String email;
    private String itemCode;
    private String description;
    private String stock;
    private String unitPrice;
    private String qty;

}
