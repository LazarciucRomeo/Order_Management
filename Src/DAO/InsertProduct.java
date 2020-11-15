package DAO;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertProduct {
    protected static final Logger LOGGER = Logger.getLogger(InsertProduct.class.getName());
    private static final String insertStatementString = "INSERT INTO Product (productName,quantity,price)" + " VALUES(?,?,?)";

    /***
     *  Introduce un produs in tabel cu ajutorul statement-ului insertStatementString = "INSERT INTO Product (productName,quantity,price)" + " VALUES(?,?,?)";
     * @param product este un parametru de tipul Product care urmeaza sa fie inserat in tabelul product
     * @return cheia primara cu care a fost inserat produsul
     */
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, product.getProductName());
            insertStatement.setInt(2, product.getQuantity());
            insertStatement.setFloat(3, product.getPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "InsertProduct:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}

