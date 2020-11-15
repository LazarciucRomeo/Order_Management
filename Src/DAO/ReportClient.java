package DAO;


import connection.ConnectionFactory;
import model.Client;
import presentation.PDF;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportClient {
    protected static final Logger LOGGER = Logger.getLogger(ReportClient.class.getName());
    private static final String reportStatementString = "Select * from client";

    /***
     * metoda report afiseaza tot tabelul sub forma de lista
     * @return tabelul client sub forma de lista
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
                int clientid = rs.getInt("clientid");
                String clientName = rs.getString("clientName");
                String address = rs.getString("address");

                toReturn.add(new Client(clientid, clientName, address));


            }
            } catch(SQLException e){
                LOGGER.log(Level.WARNING, "ClientDAO:report " + e.getMessage());
            } finally{
                ConnectionFactory.close(rs);
                ConnectionFactory.close(reportStatement);
                ConnectionFactory.close(dbConnection);
            }
            return toReturn;

        }

    }


