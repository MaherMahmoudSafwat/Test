package MenusServices.example.MenusServices.MenusService;

import MenusServices.example.MenusServices.MenusDtos.MenusDtos;
import MenusServices.example.MenusServices.MenusRepository.MenusRepository;
import MenusServices.example.MenusServices.Models.Menus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenusService
{
    private MenusRepository menusRepository;

    public MenusService(MenusRepository menusRepository)
    {
        this.menusRepository = menusRepository;
    }

    public String FindFoodPrice(String FoodName)
    {
        BigDecimal FoodPrice = menusRepository.FindByFoodName(FoodName);
        if(FoodPrice==null)
        {
            return "0";
        }
        return String.valueOf(FoodPrice);
    }

    public MenusDtos ToDto(Menus menus)
    {
        return new MenusDtos(menus.getFoodName(),menus.getFoodPrice());
    }
    public List<MenusDtos> ShowAllFoods()
    {
        List<Menus> menus = new ArrayList<>();
        menus = menusRepository.findAll();
        return menus.stream().map(this::ToDto).collect(Collectors.toList());
    }

    public Menus FoodDataDetails(String FoodName)
    {
        return menusRepository.findByFoodName(FoodName);
    }

    public void AddNewFoodDataDetails(Menus menus)
    {
         menusRepository.save(menus);
    }

    public String UpdateFoodDataDetails(String FoodName,MenusDtos FoodDataDetailsToUpdate)
    {
        Menus menus = menusRepository.findByFoodName(FoodName);
        if(menus == null)
        {
            return "The food name doesn't exists ";
        }
        else
        {
             if(FoodDataDetailsToUpdate.getFoodName()!=null)
             {
                 menus.setFoodName(FoodDataDetailsToUpdate.getFoodName());
             }
             if(FoodDataDetailsToUpdate.getFoodPrice()!=null)
             {
                 menus.setFoodPrice(FoodDataDetailsToUpdate.getFoodPrice());
             }
        }
        menusRepository.save(menus);
        return "The food data details has been updated successfully";
    }

    public String DeleteFoodDataDetails(String FoodNameToDelete)
    {
        Menus menus = menusRepository.findByFoodName(FoodNameToDelete);
        if(menus == null)
        {
            return null;
        }
        menusRepository.delete(menus);
        return "The food name data details has been deleted successfully ";
    }
}
