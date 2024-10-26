package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "itemEntity")
public class ItemEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;
    private String description;
    private String unitPrice;
    private String qty;
    private String size;
}
