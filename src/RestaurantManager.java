import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RestaurantManager {

    private ArrayList<Order> orders;


    public RestaurantManager() {
        this.orders = new ArrayList<>();
    }


    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int getOpenOrdersCount() {
        int unfinishedCount = 0;
        for (Order order : orders) {
            if (!order.isFinished()) {
                unfinishedCount++;
            }
        }
        return unfinishedCount;
    }

    public void sortOrdersByTime() {
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                return order1.getOrderedTime().compareTo(order2.getOrderedTime());
            }
        });
    }



    public double calculateAverageProcessingTime() {
        if (orders.isEmpty()) {
            return 0;
        }
        long totalProcessingTime = 0;
        int count = 0;
        for (Order order : orders) {
            if (order.getFulfilmentTime() != null) {
                totalProcessingTime += order.getProcessingTimeInMinutes();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return (double) totalProcessingTime / count;
        }
    }


    public ArrayList<Dish> getOrderedDishesToday() {
        ArrayList<Dish> orderedDishes = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Order order : orders) {
            LocalDate orderDate = order.getOrderedTime().toLocalDate();
            if (orderDate.equals(today)) {
                for (Dish dish : order.getOrderedDishes()) {
                    if (!orderedDishes.contains(dish)) {
                        orderedDishes.add(dish);
                    }
                }
            }
        }

        return orderedDishes;
    }

    public String exportOrdersForTable(int tableNumber) {
        StringBuilder output = new StringBuilder();
        String tableNumberFormatted = String.format("%2d", tableNumber).replace(' ', ' ');

        output.append("** Objednávky pro stůl č. ").append(tableNumberFormatted).append(" **\n");

        for (Order order : orders) {
            if (order.getTableNumber() == tableNumber) {
                output.append("****\n");
                output.append(order.getFormattedOrder());
                output.append("******\n");
            }
        }

        return output.toString();
    }

    public double calculateTotalBillForTable(int tableNumber) {
        double totalBill = 0;


        for (Order order : orders) {

            if (order.getTableNumber() == tableNumber) {

                for (Dish dish : order.getOrderedDishes()) {

                    totalBill += dish.getPrice() * order.getQuantity();
                }
            }
        }

        return totalBill;
    }


    public void addOrder(Order order) {
        orders.add(order);


    }
}

