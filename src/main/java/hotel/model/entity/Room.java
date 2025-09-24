package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.RoomStatus;
import hotel.model.entity.enums.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
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
