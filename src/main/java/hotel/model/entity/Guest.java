package hotel.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Guest {
    private int guestId;
    private String name;
    private String phone;
    private List<Reservation> reservations = new ArrayList<>();

    public Guest(int guestId, String name, String phone, List<Reservation> reservations) {
        this.guestId = guestId;
        this.name = name;
        this.phone = phone;
        this.reservations = reservations != null ? reservations : new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        if (this.reservations == null) {
            this.reservations = new ArrayList<>();
        }
        this.reservations.add(reservation);
    }

    public void cancelReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            reservations.remove(reservation);
            System.out.println("Reservation cancelled for guest: " + name);
        } else {
            System.out.println("Reservation not found for guest: " + name);
        }
    }

    @Override
    public String toString() {
        return "Guest {" +
                "Id=" + guestId +
                ", Name='" + name + '\'' +
                ", Phone='" + phone + '\'' +
                ", Reservations=" + reservations.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return guestId == guest.guestId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(guestId);
    }
}
