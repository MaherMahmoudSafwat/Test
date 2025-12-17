package OrdersServices.example.OrdersServices.MenusInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("MENUSSERVICES")
public interface MenusInterface
{
    @GetMapping("/Restaurant/Menus/GetFoodPrice")
    public ResponseEntity<String> FindByFoodPrice(@RequestParam String FoodName);
}
