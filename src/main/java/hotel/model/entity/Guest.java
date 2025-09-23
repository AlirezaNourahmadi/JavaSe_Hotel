package model.entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Guest implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private int guestId;
    private String name;
    private String phone;
    private List<Reservation> reservations;


    public Guest() {
        this.guestId = 0;
        this.name = "";
        this.phone = "";
        this.reservations = new ArrayList<>();
    }

    public Guest(int guestId, String name, String phone, List<Reservation> reservations) {
        if (guestId <= 0) {
            throw new IllegalArgumentException("Guest ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        this.guestId = guestId;
        this.name = name;
        this.phone = phone;
        this.reservations = reservations != null ? new ArrayList<>(reservations) : new ArrayList<>();
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        if (guestId <= 0) {
            throw new IllegalArgumentException("Guest ID must be positive");
        }
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        this.phone = phone;
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations != null ? new ArrayList<>(reservations) : new ArrayList<>();
    }


    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        this.reservations.add(reservation);
    }


    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", reservations=" + reservations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return guestId == guest.guestId &&
                Objects.equals(name, guest.name) &&
                Objects.equals(phone, guest.phone) &&
                Objects.equals(reservations, guest.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId, name, phone, reservations);
    }
}