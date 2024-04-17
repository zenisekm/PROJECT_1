import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Dish> dishes = new ArrayList<>();
        List<Order> orders = new ArrayList<>();


        try {
            FilesManager.loadData(dishes, orders);
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
            return; //
        }


        FilesManager.saveData(dishes, orders);
        System.out.println("Data byla úspěšně uložena do souborů.");


// ÚKOL 1

        try {
            FilesManager.loadData(dishes, orders);
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
            return; //
        }

        FilesManager.saveData(dishes, orders);
        System.out.println("Data byla úspěšně uložena do souborů.");


// ÚKOL 2


        dishes.add(new Dish("Kuřecí řízek obalovaný", 1, 290, 20, "rizek-obalovany-01"));
        dishes.add(new Dish("Hranolky", 2, 90, 15, "hranolky-01"));
        dishes.add(new Dish("Pstruh na víně", 3, 350, 30, "pstruh-na-vine-01"));
        dishes.add(new Dish("Kofola", 4, 55, 2, "kofola-pulitr-01"));

        try {
            FilesManager.loadData(dishes, orders); // Úkol 1
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
            return;
        }

        FilesManager.saveData(dishes, orders); // Úkol 2
        System.out.println("Data byla úspěšně uložena do souborů.");



        LocalDateTime orderedTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 20));

        Order order1 = Order.createOrder(1,15,1,2,orderedTime15,null,false,false);
        Order order2 = Order.createOrder(2,15,2,2,orderedTime15,null,false,false);
        Order order3 = Order.createOrder(3,15,4,2,orderedTime15,LocalDateTime.now(),false,false);

        LocalDateTime orderedTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 40));

        Order order4 = Order.createOrder(4,2,3,1,orderedTime2,null,false,false);
        Order order5 = Order.createOrder(5,2,2,1,orderedTime2,null,false,false);












    }}

