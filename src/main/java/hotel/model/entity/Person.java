package hotel.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String userName;
    private String password;




    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
