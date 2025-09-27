package hotel.model.entity;

import com.google.gson.Gson;

public class Property {

    private String name;
    private int quantity;



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
