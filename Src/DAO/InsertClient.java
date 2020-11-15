package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
public class InsertClient {
    protected static final Logger LOGGER = Logger.getLogger(InsertClient.class.getName());
    private static final String insertStatementString = "INSERT INTO Client (clientName,address)" + " VALUES(?,?)";

    /***
     *  Introduce un client in tabel cu ajutorul statement-ului insertStatementString = "INSERT INTO Client (clientName,address)" + " VALUES(?,?)";
     * @param client este un parametru de tipul Client care urmeaza sa fie inserat in tabelul client
     * @return cheia primara cu care a fost inserat clientul
     */
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, client.getClientName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "InsertClient:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}

