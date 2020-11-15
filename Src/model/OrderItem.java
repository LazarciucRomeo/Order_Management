package model;

public class OrderItem {
    private int orderId;
    private int clientId;
    private int price;

    /***
     * in acest tebel nu avem cheie primara
     * @param orderId este chia primara a tabelului Order si cu ajutorul urmatorului parametru are rolul de a identifica o comanda unica
     * @param clientId id-ul unui client
     * @param price urmatorul parametru ne indica pretul total al comenzii
     */
    public OrderItem(int orderId, int clientId, int price) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getPrice() {
        return price;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /***
     *
     * @return urmatoare metoda ne ofera informatii concrete despre o comanda , dar in principal ne intereseaza pretul total al comenzii
     */
    @Override
    public String toString() {
        return "OrderItem[" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", price=" + price +
                ']';
    }
}
