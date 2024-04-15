import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilesManager {


    private static final String DISHES_FILE = "dishes.txt";
    private static final String ORDERS_FILE = "orders";

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
                writer.println(dish.getName() + "," + dish.getPrice());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save dishes from file", e);
        }
    }

    private static void saveOrders(List<Order> orders) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                writer.println(order.getDish().getId()
                        + "," + order.getQuantity()
                        + "," + order.getOrderedTime()
                        + "," + order.getFulfilmentTime()
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
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                dishes.add(new Dish(id, name, price));
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
                int dishId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                long orderedTime = Long.parseLong(parts[2]);
                long fulfilmentTime = Long.parseLong(parts[3]);
                boolean isPaid = Boolean.parseBoolean(parts[4]);
                Dish dish = findDishById(dishId, dishes);
                orders.add(new Order(dish, quantity, orderedTime, fulfilmentTime, isPaid));
            }
        } catch (IOException e) {
            throw new FileLoadException("Chyba při načítání seznamu jídel ze souboru: " + e.getMessage());
        }
    }


    private static Dish findDishById(int id, List<Dish> dishes) {
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



}

