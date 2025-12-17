package UserServices.example.UserServicesRestaurant.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Sequence_Id")
    @SequenceGenerator(name = "Sequence_Id",sequenceName = "Sequence_Id",allocationSize = 1)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    public String GetUserId()
    {
        return String.valueOf(id);
    }
}
