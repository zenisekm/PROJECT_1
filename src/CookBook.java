import java.util.ArrayList;
import java.util.List;

public class CookBook {
    private List<Dish> dishes;

    public CookBook(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }



    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public void upadateDish(Dish oldDish, Dish newDish) {
        int index = dishes.indexOf(oldDish);
        if (index != -1) {
            dishes.set(index, newDish);
        } else {
            System.out.println("Dish not found");
        }
    }

   public  List<Dish> getAllDishes() {
        return new ArrayList<>(dishes);
   }
















    }












