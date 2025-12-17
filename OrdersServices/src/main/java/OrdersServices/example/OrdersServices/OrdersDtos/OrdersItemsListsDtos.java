package OrdersServices.example.OrdersServices.OrdersDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersItemsListsDtos
{
    String orderName;
    Integer orderQuantity;
    BigDecimal orderPrice;
}
