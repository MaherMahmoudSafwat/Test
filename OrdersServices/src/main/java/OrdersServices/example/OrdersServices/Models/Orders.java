package OrdersServices.example.OrdersServices.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Orders_Id")
    @SequenceGenerator(name = "Orders_Id",sequenceName = "Orders_Id",allocationSize = 1)
    private Integer id;
    @Column(unique = true)
    private String orderId;
    private String userId;
    @JsonIgnore
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrdersLists> ordersLists;
}
