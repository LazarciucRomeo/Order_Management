package DAO;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteProduct {
    protected static final Logger LOGGER = Logger.getLogger(DeleteProduct.class.getName());
    private static final String deleteStatementString = "DELETE FROM Product WHERE productName=? ";

    /***
     * -metoda delete sterge un produs din tabelul Product
     * -aceasta metoda sterge produsul a carui nume este asteptat ca parametru
     * @param productName numele produsului care urmeaza sa fie sters
     */
    public static void delete(String productName) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        //int insertedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, productName);
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            // if (rs.next()) {
            //  deletedId = rs.getInt(1);
            // }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DeleteProduct:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        // return insertedId;

    }
}
