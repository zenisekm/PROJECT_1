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

        System.out.println(dishes);


        LocalDateTime orderedTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30));
        Order order1 = Order.createOrder(1, 15, dishes.get(0), 2,false);
        Order order2 = Order.createOrder(2, 15, dishes.get(1), 2,false);
        Order order3 = Order.createOrder(3, 15, dishes.get(3), 2,true);

        LocalDateTime orderedTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 40));

        Order order4 = Order.createOrder(4, 2, dishes.get(2), 1, true);
        Order order5 = Order.createOrder(5, 2, dishes.get(2), 1, false);


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








