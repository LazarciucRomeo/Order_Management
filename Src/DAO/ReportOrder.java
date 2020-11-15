package DAO;

import connection.ConnectionFactory;
import model.Order1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportOrder {
    protected static final Logger LOGGER = Logger.getLogger(ReportOrder.class.getName());
    private static final String reportStatementString = "Select * from order1";
    /***
     * metoda report afiseaza tot tabelul sub forma de lista
     * @return tabelul order1 sub forma de lista
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
                int orderId = rs.getInt("orderId");
                int clientId = rs.getInt("clientId");
                int productId = rs.getInt("productId");
                int quantity= rs.getInt("quantity");
              String clientName = rs.getString("clientName");
              String productName = rs.getString("productName");

                toReturn.add(new Order1(orderId,clientId,productId, quantity,clientName,productName));
            }
        } catch(SQLException e){
            LOGGER.log(Level.WARNING, "OrderDAO:report " + e.getMessage());
        } finally{
            ConnectionFactory.close(rs);
            ConnectionFactory.close(reportStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

}
