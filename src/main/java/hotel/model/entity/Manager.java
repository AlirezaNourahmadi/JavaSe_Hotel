package hotel.model.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Manager extends Employee{
    private Employee managerId;
    private Person personId;



}
