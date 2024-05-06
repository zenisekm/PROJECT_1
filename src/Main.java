
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) throws FileLoadException {

                List<Dish> dishes = new ArrayList<>();
                List<Order> orders = new ArrayList<>();
// ÚKOL 1

                try {
                        FilesManager.loadData(dishes, orders);
                        System.out.println("Data byla úspěšně načtena ze souborů.");
                } catch (FileLoadException e) {
                        System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
                        return;
                }


// ÚKOL 2
                dishes.add(new Dish(1, "Kuřecí řízek obalovaný", 290));
                dishes.add(new Dish(2, "Hranolky", 90));
                dishes.add(new Dish(3, "Pstruh na víně", 350));
                dishes.add(new Dish(4, "Kofola", 60));
                dishes.add(new Dish(5, "Whiskey", 180));
                dishes.add(new Dish(6, "Panenka", 260));
                dishes.add(new Dish(7, "Losos se špenátem", 315));
                dishes.add(new Dish(8, "Smažený hermelín", 195));
                dishes.add(new Dish(9, "Sachr", 150));


                LocalDateTime orderedTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30));
                LocalDateTime orderedTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 55));
                LocalDateTime orderedTime9 = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 3));

                LocalDateTime fulfilmentTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0));
                LocalDateTime fulfilmentTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 30));
                LocalDateTime fulfilmentTime9 = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 40));



                Order order1 = Order.createOrder(1, 15, dishes.get(0), 2, false, orderedTime15, fulfilmentTime15);
                Order order2 = Order.createOrder(2, 15, dishes.get(1), 2, false, orderedTime15, fulfilmentTime15);
                Order order3 = Order.createOrder(3, 15, dishes.get(3), 2, true, orderedTime15, fulfilmentTime15);
                Order order4 = Order.createOrder(4, 2, dishes.get(5), 2, false, orderedTime2, fulfilmentTime2);
                Order order5 = Order.createOrder(5, 9, dishes.get(6), 1, true, orderedTime9, fulfilmentTime9);
                Order order6 = Order.createOrder(6, 9, dishes.get(4), 1, true, orderedTime9, fulfilmentTime9);
                Order order7 = Order.createOrder(7, 9, dishes.get(2), 2, false, orderedTime9, fulfilmentTime9);


                RestaurantManager restaurantManager = new RestaurantManager();

                restaurantManager.addOrder(order1);
                restaurantManager.addOrder(order2);
                restaurantManager.addOrder(order3);
                restaurantManager.addOrder(order4);
                restaurantManager.addOrder(order5);
                restaurantManager.addOrder(order6);
                restaurantManager.addOrder(order7);

                FilesManager.saveData(dishes, orders);
                System.out.println("Data byla úspěšně uložena do souborů.");


// ÚKOL 3

                int tableNumber = 15;
                double totalBillForTable15 = restaurantManager.calculateTotalBillForTable(tableNumber);
                System.out.println("Celková cena konzumace pro stůl číslo " + tableNumber + ": " + totalBillForTable15 + " Kč");


                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");


                // ÚKOL 4


                int openOrdersCount = restaurantManager.getOpenOrdersCount();
                System.out.println("Počet otevřených objednávek: " + openOrdersCount);

                restaurantManager.sortOrdersByTime();


                double averageProcessingTime = restaurantManager.calculateAverageProcessingTime();
                System.out.println("Průměrná doba zpracování objednávek: " + averageProcessingTime + " minut");


                ArrayList<Dish> orderedDishesToday = restaurantManager.getOrderedDishesToday();

                System.out.println("Seznam jídel objednaných dnes:");
                for (Dish dish : orderedDishesToday) {
                        System.out.println("- " + dish.getName());
                }


                    }

        }
