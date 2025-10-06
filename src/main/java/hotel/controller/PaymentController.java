package hotel.controller;

import hotel.model.entity.Payment;
import hotel.model.service.PaymentService;
import lombok.Getter;

import java.util.List;

public class PaymentController {
    @Getter
    private static final PaymentController controller = new PaymentController();

    private PaymentController() {}

    public void save(Payment payment) {
        try {
            PaymentService.getService().save(payment);
            System.out.println("Payment saved successfully: " + payment);
        } catch (Exception e) {
            System.out.println("Error saving payment: " + e.getMessage());
        }
    }

    public void edit(Payment payment) {
        try {
            PaymentService.getService().edit(payment);
            System.out.println("Payment updated successfully: " + payment);
        } catch (Exception e) {
            System.out.println("Error updating payment: " + e.getMessage());
        }
    }

    public void delete(int paymentId) {
        try {
            PaymentService.getService().delete(paymentId);
            System.out.println("Payment deleted with ID: " + paymentId);
        } catch (Exception e) {
            System.out.println("Error deleting payment: " + e.getMessage());
        }
    }

    public List<Payment> findAll() {
        try {
            return PaymentService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching payments: " + e.getMessage());
            return null;
        }
    }

    public Payment findById(int paymentId) {
        try {
            return PaymentService.getService().findById(paymentId);
        } catch (Exception e) {
            System.out.println("Error finding payment: " + e.getMessage());
            return null;
        }
    }
}