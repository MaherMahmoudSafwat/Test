package OrdersServices.example.OrdersServices.OrdersController;

import OrdersServices.example.OrdersServices.Models.OrdersLists;
import OrdersServices.example.OrdersServices.OrdersDtos.OrdersDtos;
import OrdersServices.example.OrdersServices.OrdersDtos.OrdersItemsListsDtos;
import OrdersServices.example.OrdersServices.OrdersService.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Restaurant/Orders")
public class OrdersController
{
    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService)
    {
        this.ordersService = ordersService;
    }

    @PostMapping("/Orders")
    public ResponseEntity<?> RequestAnOrder
            (
            @RequestHeader("X-UserId") String userId,
            @RequestBody OrdersDtos ordersDtos
            )
    {
        String orderId = ordersService.OrderRequest(userId, ordersDtos);
        Map<String, List<String>> Responses = new HashMap<>();
        if(orderId.equals("00000"))
        {
            Responses.put("The following food names doesn't exist here :- ",ordersService.getUsersOrdersNotFoundData());
            return new ResponseEntity<>(Responses,HttpStatus.NO_CONTENT);
        }
        List<String>UserOrdersId = new ArrayList<>();
        if(!ordersService.getUsersOrdersNotFoundData().isEmpty())
        {
            Responses.put("The following food names doesn't exist here :- ",ordersService.getUsersOrdersNotFoundData());
            UserOrdersId.add(orderId);
            Responses.put("The User Order Id is :- ",UserOrdersId);
            return new ResponseEntity<>(Responses,HttpStatus.CREATED);
        }
        Responses.put("The User Order Id is :- ",UserOrdersId);
        return new ResponseEntity<>(Responses,HttpStatus.CREATED);
    }

    @GetMapping("/GetOrdersPrice")
    public ResponseEntity<Object> GetUsersOrders(@RequestParam String OrderId, @RequestHeader("X-UserName") String UserName)
    {
        Optional<List<OrdersItemsListsDtos>> ListOfItems = ordersService.GetUserOrders(OrderId,UserName);
        if(ListOfItems.isPresent())
        {
            return new ResponseEntity<>(ListOfItems,HttpStatus.OK);
        }
        return new ResponseEntity<>("The OrderId " + OrderId + " is not found ",HttpStatus.NOT_FOUND);
    }
}
