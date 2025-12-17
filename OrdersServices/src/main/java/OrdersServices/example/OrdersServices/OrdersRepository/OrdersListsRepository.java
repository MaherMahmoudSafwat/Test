package OrdersServices.example.OrdersServices.OrdersRepository;

import OrdersServices.example.OrdersServices.Models.OrdersLists;
import OrdersServices.example.OrdersServices.OrdersDtos.OrdersItemsListsDtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersListsRepository extends JpaRepository<OrdersLists, Integer>
{
    @Query
            (
                    value = "SELECT * FROM orders_lists WHERE order_id = :id",
                    nativeQuery = true
            )
    public Optional<List<OrdersLists>> findByOrderId(@Param("id") Integer id);
}
