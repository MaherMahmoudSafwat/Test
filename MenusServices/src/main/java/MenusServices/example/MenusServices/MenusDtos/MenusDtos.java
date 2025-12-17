package MenusServices.example.MenusServices.MenusDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenusDtos
{
    String foodName;
    BigDecimal foodPrice;
}
