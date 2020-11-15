package model;


public class Client {
    private int clientId;
    private String clientName;
    private String address;

    /***
     *  Constructorul este folosit pentru a seta starea internă a obiectului
     * @param clientid este cheia primara a tabelului
     * @param clientName -numele clientului care este extras din fisierul text,urmand sa fie adaugat in tabel
     * @param address -Stringul care exact ca si clientName este extras in fisierul text
     */
    public Client(int clientid, String clientName, String address) {
        this.clientId = clientid;
        this.clientName = clientName;
        this.address = address;
    }

    public void setClientid(int clientid) {
        this.clientId = clientid;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClientid() {
        return clientId;
    }

    /***
     *
     * @return numele clientului
     */
    public String getClientName() {
        return clientName;
    }

    /***
     *
     * @return adresa clientului
     */
    public String getAddress() {
        return address;
    }

    /***
     * Returneaza sub forma de mai jos informatii referitoare la unul din clienti
     * @return informatii despre un client
     */

    @Override
    public String toString() {
        return "Client[" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", address='" + address + '\'' +
                ']';
    }
}
