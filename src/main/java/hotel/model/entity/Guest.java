package hotel.model.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Guest extends Person{
    private Person guestId;
}