package DAO;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Update {

    protected static final Logger LOGGER = Logger.getLogger(InsertProduct.class.getName());
    private static final String updateStatementString = "UPDATE Product SET quantity= ? WHERE productId = ?";

    /***
     *metoda update reinoieste in tabelul product cantitatea dupa ce a fost plasata o comanda
     * @param quantity cantitatea ramasa dupa comanda
     * @param productId id-ul produsului la care trebuie reinnoita cantitatea
     */
    public static void update(int quantity,int productId) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;

        try {
           updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);

            updateStatement .setInt(1, quantity);
            updateStatement .setInt(2, productId);

            updateStatement .executeUpdate();

            ResultSet rs =  updateStatement .getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UpdateProduct:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(  updateStatement );
            ConnectionFactory.close(dbConnection);
        }

    }
}


