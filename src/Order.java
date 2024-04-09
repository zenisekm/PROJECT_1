import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {


    private Dish dish;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean isPaid;

    public Order(Dish dish, int quantity, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, boolean isPaid) {
        this.dish = dish;
        this.quantity = quantity;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.isPaid = isPaid;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }


    public void updateOrder(int newQuantity) {
        if (newQuantity > 0) {
            this.quantity = newQuantity;
        } else {
            System.out.println("Quantity must be a positive number");
        }
    }

    public void setfulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }


    public void orderPaided() {
        this.isPaid = true;
    }


    public static List<Order> getAllOrders() {
        return new ArrayList<>(getAllOrders());

    }


//   public String toString() {
//       return "Order{" +
//               "dish=" + dish.getName() +
//               ", quantity=" + quantity +
//               ", orderedTime=" + orderedTime +
//               ", fulfilmentTime=" + fulfilmentTime +
//               ", isPaid=" + isPaid +
//               '}';


//   }



}







