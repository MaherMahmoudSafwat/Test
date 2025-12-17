package OrdersServices.example.OrdersServices.OrdersRepository;

import OrdersServices.example.OrdersServices.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer>
{
    @Query
            (
                    value = "SELECT id FROM Orders WHERE order_id=:orderId",
                    nativeQuery = true
            )
    public Integer fndByOrderId(@Param("orderId") String orderId);
}
