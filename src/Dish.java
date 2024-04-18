public class Dish {

    private String name;
    private int id;
    private double price;
    private int preparationTiome;
    private String photoUrl;

    public Dish(String name, int id, double price, int preparationTime, String photoUrl) {
        this.name = name;
        this.id = id;
        this.price = price;
        if (preparationTiome >= 0) {
            this.preparationTiome = preparationTiome;
        }else{
            throw new IllegalArgumentException("Preparation time can't be a negative number");
        }
        this.photoUrl = photoUrl;
    }


    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public Dish(int id, String name, double price) {

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

    public int getPreparationTiome() {
        return preparationTiome;
    }

    public void setPreparationTiome(int preparationTiome) {
        this.preparationTiome = preparationTiome;
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
