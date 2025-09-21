package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.enums.RoomStatus;
import hotel.model.enums.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Room {
    private int roomId;
    private RoomType type;
    private RoomStatus status;
    private int pricePerNight;
    private int capacity;

   // isAvailable()
   @Override
   public String toString() {
       Gson gson = new Gson();
       return gson.toJson(this);
   }

}
