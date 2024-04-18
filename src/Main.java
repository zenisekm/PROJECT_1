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


        dishes.add(new Dish("Kuřecí řízek obalovaný",290));
        dishes.add(new Dish("Hranolky",90));
        dishes.add(new Dish("Pstruh na víně",350));
        dishes.add(new Dish("Kofola",55));


        LocalDateTime orderedTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 20));

        Order order1 = Order.createOrder(1, 15, 1, 2, orderedTime15, null, false, false);
        Order order2 = Order.createOrder(2, 15, 2, 2, orderedTime15, null, false, false);
        Order order3 = Order.createOrder(3, 15, 4, 2, orderedTime15, LocalDateTime.now(), false, false);

        LocalDateTime orderedTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 40));

        Order order4 = Order.createOrder(4, 2, 3, 1, orderedTime2, null, false, false);
        Order order5 = Order.createOrder(5, 2, 2, 1, orderedTime2, null, false, false);

// ÚKOL 3

        RestaurantManager restaurantManager = new RestaurantManager();

        restaurantManager.addOrder(order1);
        restaurantManager.addOrder(order2);
        restaurantManager.addOrder(order3);

        int tableNumber = 15;
        double totalBillForTable15 = restaurantManager.calculateTotalBillForTable(tableNumber);

        System.out.println("Celková cena konzumace pro stůl číslo " + tableNumber + ": " + totalBillForTable15 + " Kč");



        try {
            FilesManager.loadData(dishes, orders);
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
            return; //
        }

        FilesManager.saveData(dishes, orders);
        System.out.println("Data byla úspěšně uložena do souborů.");



    }
}








