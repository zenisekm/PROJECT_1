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
    }


}