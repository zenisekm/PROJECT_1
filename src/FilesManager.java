import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilesManager {

    private static final String DISHES_FILE = "dishes.txt";
    private static final String ORDERS_FILE = "orders.txt";

    private static final Logger logger = Logger.getLogger(FilesManager.class.getName());

    public static void saveData (List<Dish> dishes, List<Order> orders) {
        saveDishes(dishes);
        saveOrders(orders);

    }


    public static void loadData (List<Dish> dishes, List<Order> orders) throws FileLoadException {
        loadDishes(dishes);
        loadOrders(orders, dishes);
    }

    private static void saveDishes(List<Dish> dishes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DISHES_FILE))) {
            for (Dish dish : dishes) {
                writer.println(dish.getId() + "," + dish.getName() + "," + dish.getPrice());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save dishes from file", e);
        }
    }



    private static void saveOrders(List<Order> orders) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                LocalDateTime orderedTime = order.getOrderedTime();
                LocalDateTime fulfilmentTime = order.getFulfilmentTime();
                writer.println(order.getId()
                        + "," + order.getTableNumber() // Přidáme tableNumber
                        + "," + order.getQuantity()
                        + "," + (orderedTime != null ? orderedTime.toEpochSecond(ZoneOffset.UTC) : "") // Pokud je čas objednání null, zapiš prázdný řetězec
                        + "," + (fulfilmentTime != null ? fulfilmentTime.toEpochSecond(ZoneOffset.UTC) : "") // Pokud je čas splnění null, zapiš prázdný řetězec
                        + "," + order.isPaid());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save orders from file", e);
        }
    }


    private static void loadDishes(List<Dish> dishes) throws FileLoadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(DISHES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim()); // Získání ID jídla a odstranění bílých znaků
                    String name = parts[1].trim(); // Získání názvu jídla a odstranění bílých znaků
                    double price = Double.parseDouble(parts[2].trim()); // Získání ceny jídla a odstranění bílých znaků
                    dishes.add(new Dish(id, name, price)); // Vytvoření nového jídla a přidání do seznamu
                } else {
                    throw new FileLoadException("Neplatný formát řádku v souboru dishes.txt: " + line);
                }
            }
        } catch (IOException e) {
            throw new FileLoadException("Chyba při načítání seznamu jídel ze souboru: " + e.getMessage());
        }
    }


    private static void loadOrders(List<Order> orders, List<Dish> dishes) throws FileLoadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].isEmpty()) {
                    int dishId = Integer.parseInt(parts[0].trim());
                    int quantity = Integer.parseInt(parts[1].trim());
                    long orderedTimeEpochSeconds = Long.parseLong(parts[2].trim());
                    long fulfilmentTimeEpochSeconds = Long.parseLong(parts[3].trim());
                    LocalDateTime orderedTime = LocalDateTime.ofEpochSecond(orderedTimeEpochSeconds, 0, ZoneOffset.UTC); // Převést sekundy na LocalDateTime
                    LocalDateTime fulfilmentTime = LocalDateTime.ofEpochSecond(fulfilmentTimeEpochSeconds, 0, ZoneOffset.UTC); // Převést sekundy na LocalDateTime
                    boolean isPaid = Boolean.parseBoolean(parts[4].trim());
                    Dish dish = findDishById(dishId, dishes);
                    orders.add(Order.createOrder(orders.size() + 1, 15, dish, quantity, isPaid, orderedTime)); // Přidat novou objednávku s časem objednání
                } else {
                    // Obsluha případu, kdy je řetězec prázdný
                }
            }
        } catch (IOException e) {
            throw new FileLoadException("Chyba při načítání seznamu jídel ze souboru: " + e.getMessage());
        }
    }



    public static Dish findDishById(int id, List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                return dish;
            }
        }
        return null;


    }

    public static void loadInitialState(List<Dish> dishes, List<Order> orders) {
        try {
            loadData(dishes, orders);
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());

        }
    }

    public static double getTotalBillForTable(List<Order> orders, int tableNumber) {
        double totalBill = 0;
        for (Order order : orders) {
            if (order.getTableNumber() == tableNumber) { // Kontrola, zda objednávka je pro požadovaný stůl
                totalBill += order.getDish().getPrice() * order.getQuantity(); // Sečíst cenu objednaných jídel
            }
        }
        return totalBill;
    }

}