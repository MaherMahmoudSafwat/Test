package MenusServices.example.MenusServices.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menus
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Menu_Id")
    @SequenceGenerator(name = "Menu_Id",sequenceName = "Menu_Id",allocationSize = 1)
    Integer id;
    String foodName;
    BigDecimal foodPrice;
}
