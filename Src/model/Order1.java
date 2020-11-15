package model;

public class Order1 {
    private int orderId;
    private int clientId;
    private int productId;
    private int quantity;
    private String clientName;
    private String productName;

    /***
     *Aici este creat tabelul
     * Rolul constructorului este de a  seta starea internă a obiectului
     * @param orderId este cheia primara a tabelului
     * @param clientId este id-ul clientul(cheia primara a celuilalt tabel), pentru a nu fii incurcate comenzile in caz ca avem clienti cu acelasi nume
     * @param producId este id-ul produsului(cheia primara a tabelului Product),are acelasi scop ca si clientid
     * @param quantity este parametrul care ne specifica cantitatea produsului
     * @param clientName avand in vedere ca tabelul reprezinta o comanda avem nevoie si de numele Clientului
     * @param productName este parametrul care specifica ce anume isi comanda clietul
     */
    public Order1(int orderId, int clientId, int producId, int quantity, String clientName, String productName) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.productId = producId;
        this.quantity = quantity;
        this.clientName = clientName;
        this.productName = productName;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setProducId(int producId) {
        this.productId = producId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProducId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public String getProductName() {
        return productName;
    }

    /***
     *
     * @return toate informatiile referitoare la o comanda
     */
    @Override
    public String toString() {
        return "Order[" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", producId=" + productId +
                ", quantity=" + quantity +
                ", clientName='" + clientName + '\'' +
                ", productName='" + productName + '\'' +
                ']';
    }

}
