public class Dish {

    private String name;
    private double price;
    private int preparationTiome;
    private String photoUrl;

    public Dish(String name, double price, int preparationTiome, String photoUrl) {
        this.name = name;
        this.price = price;
        if (preparationTiome >= 0) {
            this.preparationTiome = preparationTiome;
        }else{
            throw new IllegalArgumentException("Preparation time can't be a negative number");
        }
        this.photoUrl = photoUrl;
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



}
