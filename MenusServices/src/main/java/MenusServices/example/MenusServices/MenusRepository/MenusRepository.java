package MenusServices.example.MenusServices.MenusRepository;

import MenusServices.example.MenusServices.Models.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MenusRepository extends JpaRepository<Menus,Integer>
{
    @Query
            (
                    value = "SELECT food_price FROM menus WHERE food_name = :food_name",
                    nativeQuery = true
            )
    public BigDecimal FindByFoodName(@Param("food_name")String food_name);
    public Menus findByFoodName(String FoodName);
}
