package DAO;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportProduct {
    protected static final Logger LOGGER = Logger.getLogger(ReportProduct.class.getName());
    private static final String reportStatementString = "Select * from product";
    /***
     * metoda report afiseaza tot tabelul sub forma de lista
     * @return tabelul product sub forma de lista
     */
    public static ArrayList<Object> report() {
        ArrayList<Object> toReturn = new ArrayList<Object>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement reportStatement = null;
        ResultSet rs = null;
        try {
            reportStatement = dbConnection.prepareStatement(reportStatementString);

            rs = reportStatement.executeQuery();
            // rs.next();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");

                toReturn.add(new Product(productId, productName, quantity,price));
            }
        } catch(SQLException e){
            LOGGER.log(Level.WARNING, "ProductDAO:report " + e.getMessage());
        } finally{
            ConnectionFactory.close(rs);
            ConnectionFactory.close(reportStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
