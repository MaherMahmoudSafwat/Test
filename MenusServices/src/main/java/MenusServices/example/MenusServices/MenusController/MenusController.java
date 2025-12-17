package MenusServices.example.MenusServices.MenusController;

import MenusServices.example.MenusServices.MenusDtos.MenusDtos;
import MenusServices.example.MenusServices.MenusService.MenusService;
import MenusServices.example.MenusServices.Models.Menus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Restaurant/Menus")
public class MenusController
{
    private MenusService menusService;

    public MenusController(MenusService menusService)
    {
        this.menusService = menusService;
    }

    @GetMapping("/ShowAllMenus")
    public ResponseEntity<?> ShowAllMenu()
    {
        List<MenusDtos>menusDtos = menusService.ShowAllFoods();
        return menusDtos.isEmpty()?
                new ResponseEntity<>("There is no menu available ", HttpStatus.NOT_FOUND):
                new ResponseEntity<>(menusDtos,HttpStatus.FOUND);
    }

    @GetMapping("/GetFoodPrice")
    public ResponseEntity<String> FindByFoodPrice(@RequestParam String FoodName)
    {
        String FoodPrice = menusService.FindFoodPrice(FoodName);
        return (!FoodPrice.equals("0"))?
                new ResponseEntity<>(String.valueOf(FoodPrice),HttpStatus.OK):
                new ResponseEntity<>( "There is no Food Data details for these name available or exists ",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/AddNewFood")
    public ResponseEntity<String> AddNewFood(@RequestBody Menus menus)
    {
        menusService.AddNewFoodDataDetails(menus);
        return new ResponseEntity<>("The food has been added successfully ",HttpStatus.CREATED);
    }

    @DeleteMapping("/DeleteAnExistingFood/{FoodName}")
    public ResponseEntity<String> DeleteAnExistingFood(@PathVariable String FoodName)
    {
        String MenusService = menusService.DeleteFoodDataDetails(FoodName);
        return (MenusService==null)?
                new ResponseEntity<>("The food data details are are not found or available",HttpStatus.NOT_FOUND):
                new ResponseEntity<>(MenusService,HttpStatus.FOUND);
    }

    @PutMapping("/UpdateExistingFoodDetails/{FoodName}")
    public ResponseEntity<String> UpdateExistingFoodDataDetails(@PathVariable String FoodName,@RequestBody MenusDtos menusDtos)
    {
        String UpdateExistingFoodDataDetails = menusService.UpdateFoodDataDetails(FoodName,menusDtos);
        return new ResponseEntity<>(UpdateExistingFoodDataDetails,HttpStatus.OK);
    }
}