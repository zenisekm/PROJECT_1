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


        dishes.add(new Dish("Kuřecí řízek obalovaný",1, 290, 20, "rizek-obalovany-01"));
        dishes.add(new Dish("Hranolky",2, 90, 15, "hranolky-01"));
        dishes.add(new Dish("Pstruh na víně",3, 350, 30, "pstruh-na-vine-01"));
        dishes.add(new Dish("Kofola",4, 55, 2, "kofola-pulitr-01"));

        try {
            FilesManager.loadData(dishes, orders); // Úkol 1
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
            return;
        }

        FilesManager.saveData(dishes, orders); // Úkol 2
        System.out.println("Data byla úspěšně uložena do souborů.");
    }











    }

