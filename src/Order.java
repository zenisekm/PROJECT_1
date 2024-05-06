import java.time.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Order {


    private ArrayList<Dish> orderedDishes;


    private Dish dish;

    private int id;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean isPaid;

    private boolean isFinished;

    private int tableNumber;


    public Order(ArrayList<Dish> orderedDishes, Dish dish, int id, int quantity, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, boolean isPaid, boolean isFinished, int tableNumber) {
        this.orderedDishes = orderedDishes;
        this.dish = dish;
        this.id = id;
        this.quantity = quantity;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.isPaid = isPaid;
        this.isFinished = isFinished;
        this.tableNumber = tableNumber;
    }

    public Order(Dish dish, int quantity, long orderedTime, long fulfilmentTime, boolean isPaid) {

    }


    public Order(Dish dish, int i, int id, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, boolean isPaid, boolean isFinished, int tableNumber) {



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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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


    public String toString() {
        return "Order{" +
                "dish=" + dish.getName() +
                ", quantity=" + quantity +
                ", orderedTime=" + orderedTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", isPaid=" + isPaid +
                '}';
    }

    public long getProcessingTimeInMinutes() {
        Duration duration = Duration.between(orderedTime, fulfilmentTime);
        return duration.toMinutes();
    }

    public ArrayList<Dish> getOrderedDishes() {
        return orderedDishes;
    }


    public String getFormattedOrderWithFulfilment() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String paidString = isPaid() ? "zaplaceno" : "";
        String fulfilmentTimeString = getFulfilmentTime() != null ? getFulfilmentTime().format(formatter) : "";

        return String.format("%d. %s %dx (%.1f Kč): %s-%s %s\n", getId(), getDish().getName(), getQuantity(), getDish().getPrice() * getQuantity(), getOrderedTime().format(formatter), fulfilmentTimeString, paidString);
    }


    public int getTableNumber() {
        return tableNumber;
    }


    public static Order createOrder(int id, int tableNumber, Dish dish, int quantity, boolean isPaid, LocalDateTime orderedTime, LocalDateTime fulfilmentTime) {
        return new Order(new ArrayList<>(Collections.singletonList(dish)), dish, id, quantity, orderedTime, fulfilmentTime, isPaid, false, tableNumber);
    }







}