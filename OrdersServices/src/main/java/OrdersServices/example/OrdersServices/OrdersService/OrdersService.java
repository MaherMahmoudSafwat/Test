package OrdersServices.example.OrdersServices.OrdersService;

import OrdersServices.example.OrdersServices.MenusInterface.MenusInterface;
import OrdersServices.example.OrdersServices.Models.Orders;
import OrdersServices.example.OrdersServices.Models.OrdersLists;
import OrdersServices.example.OrdersServices.OrdersDtos.OrdersDtos;
import OrdersServices.example.OrdersServices.OrdersDtos.OrdersItemsListsDtos;
import OrdersServices.example.OrdersServices.OrdersRepository.OrdersListsRepository;
import OrdersServices.example.OrdersServices.OrdersRepository.OrdersRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class OrdersService
{

    private OrdersRepository ordersRepository;
    private OrdersListsRepository ordersListsRepository;
    private List<String> UsersOrdersNotFoundData = new ArrayList<>();
    private MenusInterface menusInterface;

    public OrdersService
            (
                    OrdersRepository ordersRepository,
                    MenusInterface menusInterface,
                    OrdersListsRepository ordersListsRepository
            )
    {
        this.ordersRepository = ordersRepository;
        this.menusInterface = menusInterface;
        this.ordersListsRepository = ordersListsRepository;
    }

    private List<OrdersLists> AddNewOrdersLists(Orders UserOrders, OrdersDtos ordersDtos)
    {
        List<OrdersLists> ordersLists = new ArrayList<>();

        for(Map.Entry<String,Integer> orders: ordersDtos.getOrders().entrySet())
        {
            OrdersLists ListsOfOrders = new OrdersLists();
            String OrderName = orders.getKey();

            try
            {
                Object foodPriceObj = menusInterface.FindByFoodPrice(orders.getKey()).getBody();

                if (foodPriceObj instanceof String) {
                    String foodPriceString = (String) foodPriceObj;
                    if (foodPriceString.equals("There is no Food Data details for these name available or exists ")) {
                        UsersOrdersNotFoundData.add(OrderName);
                        continue;
                    }
                }
            }
            catch ()
            {

            }

            if(foodPriceObj instanceof BigDecimal) {
                BigDecimal foodPrice = (BigDecimal) foodPriceObj;
                ListsOfOrders.setOrderName(orders.getKey());
                ListsOfOrders.setOrderQuantity(orders.getValue());
                ListsOfOrders.setOrderPrice(foodPrice);
                ListsOfOrders.setOrders(UserOrders);
                ordersLists.add(ListsOfOrders);
            } else {
                UsersOrdersNotFoundData.add(OrderName);
            }
        }
        return ordersLists;
    }
    @Transactional
    public String OrderRequest(String UserId, OrdersDtos ordersDtos)
    {
        List<OrdersLists> ordersLists = new ArrayList<>();
        Orders UserOrders = new Orders();
        String UserOrderId = "ORD"+ UUID.randomUUID().toString();
        UserOrders.setOrderId(UserOrderId);
        ordersLists = AddNewOrdersLists(UserOrders,ordersDtos);
        if(ordersLists.isEmpty())
        {
            return "00000";
        }
        UserOrders.setOrdersLists(ordersLists);
        UserOrders.setUserId(UserId);
        ordersRepository.save(UserOrders);
        return UserOrderId;
    }
    public OrdersItemsListsDtos ToDto(OrdersLists ordersLists)
    {
        return new OrdersItemsListsDtos(ordersLists.getOrderName(),ordersLists.getOrderQuantity(),ordersLists.getOrderPrice());
    }
    public Optional<List<OrdersItemsListsDtos>> GetUserOrders(String OrderId, String UserName) {
        Integer Id = ordersRepository.fndByOrderId(OrderId);
        if (Id == null) {
            return Optional.empty();
        }
        Optional<List<OrdersLists>> orderListsOptional = ordersListsRepository.findByOrderId(Id);

        Optional<List<OrdersItemsListsDtos>> GetAllUserOrders = orderListsOptional
                .map(orderLists -> orderLists.stream()
                        .map(this::ToDto)
                        .collect(Collectors.toList())
                );

        return GetAllUserOrders;
    }
}
