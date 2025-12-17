package OrdersServices.example.OrdersServices.OrdersDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.mail.MailParseException;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDtos
{
    Map<String,Integer> orders;
}
