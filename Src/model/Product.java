package model;

public class Product {
    private int productId;
    private String productName;
    private int quantity;
    private float price;

    /***
     *
     * @param productId  cheia primara a tabelului
     * @param productName numele produsului
     * @param quantity cantitatea produsului
     * @param price pretul produsului
     */
    public Product(int productId, String productName, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    /***
     *
     * @return numele produsului din tabel
     */
    public String getProductName() {
        return productName;
    }

    /***
     *
     * @return cantitatea unui anumit produs la momentul respectiv
     */
    public int getQuantity() {
        return quantity;
    }

    /***
     *
     * @return pretul unui produs, pretul/1 cantitate din prdusul respectiv
     */
    public float getPrice() {
        return price;
    }

    /***
     *
     * @return toate informatiile despre un prdus din tabel
     */
    @Override
    public String toString() {
        return "Product[" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ']';
    }
}
