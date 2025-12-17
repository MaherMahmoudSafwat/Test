package OrdersServices.example.OrdersServices.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersLists
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OrdersLists_Id")
    @SequenceGenerator(name = "OrdersLists_Id",sequenceName = "OrdersLists_Id",allocationSize = 1)
    private Integer id;
    Integer orderQuantity;
    String orderName;
    BigDecimal orderPrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Orders orders;
}
