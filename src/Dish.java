public class Dish {

    private String name;
    private int id;
    private double price;
    private int preparationTime;
    private String photoUrl;

    public Dish(String name, int id, double price, int preparationTime, String photoUrl) {
        this.name = name;
        this.id = id;
        this.price = price;
        if (preparationTime >= 0) {
            this.preparationTime = preparationTime;
        }else{
            throw new IllegalArgumentException("Preparation time can't be a negative number");
        }
        this.photoUrl = photoUrl;
    }





    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}