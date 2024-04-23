import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Dish> dishes = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

// ÚKOL 1




// ÚKOL 2


        dishes.add(new Dish(1,"Kuřecí řízek obalovaný",290));
        dishes.add(new Dish(2,"Hranolky",90));
        dishes.add(new Dish(3,"Pstruh na víně",350));
        dishes.add(new Dish(4,"Kofola",55));







        LocalDateTime orderedTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30));
        LocalDateTime orderedTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 40));


        orders.add(Order.createOrder(1, 15, dishes.get(0), 2, false, orderedTime15));
        orders.add(Order.createOrder(2, 15, dishes.get(1), 2, false, orderedTime15));
        orders.add(Order.createOrder(3, 15, dishes.get(3), 2, true, orderedTime15));
        orders.add(Order.createOrder(4, 2, dishes.get(2), 1, true, orderedTime2));
        orders.add(Order.createOrder(5, 2, dishes.get(2), 1, false, orderedTime2));


        for (Order order : orders) {
            System.out.println(order);
        }





// ÚKOL 3

        RestaurantManager restaurantManager = new RestaurantManager();



        int tableNumber = 15;
        double totalBillForTable15 = FilesManager.getTotalBillForTable(orders, tableNumber);
        System.out.println("Celková cena konzumace pro stůl číslo " + tableNumber + ": " + totalBillForTable15 + " Kč");




    }
}








