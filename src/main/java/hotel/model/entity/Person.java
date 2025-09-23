package hotel.model.entity;

import com.google.gson.Gson;

import java.time.LocalDate;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private String address;




    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
