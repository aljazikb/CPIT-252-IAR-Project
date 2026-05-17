public class Product {

    private int id;
    private String productName;
    private int quantity;
    private double price;
    private String category;


    public Product(int id,String productName,int quantity,double price,String category){
        this.id=id;
        this.productName=productName;
        this.quantity=quantity;
        this.price=price;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
    public String getCategory()   {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


}
