
package DAO;

        import connection.ConnectionFactory;
        import model.Product;

        import java.sql.*;
        import java.util.logging.Level;
        import java.util.logging.Logger;

public class Order {
    protected static final Logger LOGGER = Logger.getLogger(InsertProduct.class.getName());
    private static final String orderStatementString = "INSERT INTO Order1 (clientId,productId,quantity,clientName,productName)" + " VALUES(?,?,?,?,?)";
    private static final String order1StatementString = "Select clientId from Client where clientName= ?";
    private static final String order2StatementString = "Select productId from Product where productName= ?";
    private static final String order3StatementString = "Select price from Product where productName= ?";
    private static final String order4StatementString = "INSERT INTO OrderItem (orderId,clientId,price)" + " VALUES(?,?,?)";
    private static final String order5StatementString = "Select orderId from Order1 where clientName= ?";
    private static final String order6StatementString = "Select quantity from Product where productName= ?";

    /***
     *  Metoda order insereaza o comanda in tabel
     * @param clientId  este id-ul clientului
     * @param productId id-ul produsului
     * @param quantity cantitatea de prdus care a fost comandata
     * @param clientName numele Clientului care a comandat
     * @param productName numele produsului  comandat
     * @return cheia primara cu care a fost introdusa comanda  in tabelul Order1
     */
    public static int order(int clientId,int productId ,int quantity, String clientName,String productName) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement orderStatement = null;
        int insertedId = -1;
        try {
           orderStatement = dbConnection.prepareStatement(orderStatementString, Statement.RETURN_GENERATED_KEYS);

            orderStatement.setInt(1,clientId );
            orderStatement.setInt(2,productId );
          orderStatement.setInt(3,quantity );
            orderStatement.setString(4,clientName );
            orderStatement.setString(5,productName );



        orderStatement.executeUpdate();

            ResultSet rs = orderStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderProduct:order " + e.getMessage());
        } finally {
            ConnectionFactory.close(orderStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /***
     * -aceasta metoda cauta id-ul clientului trimis ca parametru
     * @param clientName numele clientului a carui id vrem sa il cautam
     * @return id-ul clientului cautat
     */
    public  int findByclientName(String clientName) {
      int toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement order1Statement = null;
        ResultSet rs = null;
        try {
          order1Statement = dbConnection.prepareStatement(order1StatementString);
          order1Statement.setString(1, clientName);
            rs = order1Statement.executeQuery();
            rs.next();

          int clientid = rs.getInt("clientid");


            toReturn = clientid;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findByclientName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(order1Statement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /***
     * -aceasta metoda cauta id-ul produsului  trimis ca parametru
     * @param productName numele produsului  a carui id vrem sa il cautam
     * @return id-ul produsului  cautat
     */
    public  int findByproductName(String productName) {
        int toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement order2Statement = null;
        ResultSet rs = null;
        try {
            order2Statement = dbConnection.prepareStatement(order2StatementString);
            order2Statement.setString(1, productName);
            rs = order2Statement.executeQuery();
            rs.next();

            int productid = rs.getInt("productid");


            toReturn = productid;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findByproductName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(order2Statement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /***
     * -aceasta metoda cauta cantitatea produsului  trimis ca parametru
     * @param productName numele produsului  a carui cantitate vrem sa o cautam
     * @return cantitatea produsului cautat
     */
    public  int findproductQuantity(String productName) {
        int toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement order6Statement = null;
        ResultSet rs = null;
        try {
            order6Statement = dbConnection.prepareStatement(order6StatementString);
            order6Statement.setString(1, productName);
            rs = order6Statement.executeQuery();
            rs.next();

            int quantity = rs.getInt("quantity");


            toReturn = quantity;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findByproductName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(order6Statement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /***
     * -aceasta metoda cauta pretul produsului  trimis ca parametru
     * @param productName numele produsului a carui pret vrem sa il cautam
     * @return pretul produsului cautat
     */
    public  int findPrice(String productName) {
        int toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement order3Statement = null;
        ResultSet rs = null;
        try {
            order3Statement = dbConnection.prepareStatement(order3StatementString);
            order3Statement.setString(1, productName);
            rs = order3Statement.executeQuery();
            rs.next();

            int price = rs.getInt("price");


            toReturn = price;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findPrice" + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(order3Statement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /***
     * -acesta metoda inseraza in tabelul OrderItem
     * @param orderId id-ul comenzii
     * @param clientId id-ul clientului
     * @param price pretul total al comenzii
     */
    public static void orderWithPrice(int orderId,int clientId ,int price) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement order4Statement = null;

        try {
            order4Statement = dbConnection.prepareStatement(order4StatementString, Statement.RETURN_GENERATED_KEYS);

            order4Statement.setInt(1,orderId );
            order4Statement.setInt(2,clientId );
            order4Statement.setInt(3,price );



            order4Statement.executeUpdate();


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderProduct:order " + e.getMessage());
        } finally {
            ConnectionFactory.close(order4Statement);
            ConnectionFactory.close(dbConnection);
        }

    }

    /**
     * metoda urmatoare cauta id-ul unei comenzi cu ajutorul numelui unui client
     * @param clientName numele clientului
     * @return id-ul comenzii
     */
    public  int findorderID(String clientName) {
        int toReturn = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement order5Statement = null;
        ResultSet rs = null;
        try {
            order5Statement = dbConnection.prepareStatement(order5StatementString);
            order5Statement.setString(1, clientName);
            rs = order5Statement.executeQuery();
            rs.next();

            int orderId = rs.getInt("orderId");


            toReturn = orderId;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findPrice" + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(order5Statement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}


