package hotel.model.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class AssignProperty extends Property {
    private int propertyId;
    private int quantity;
    private Room room;
    private LocalDate assignedDate;
    private List<Employee> assignedBy;





}
