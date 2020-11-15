package DAO;

import connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteClient {

    protected static final Logger LOGGER = Logger.getLogger(DeleteClient.class.getName());
    private static final String deleteStatementString = "DELETE FROM Client WHERE clientName=? AND address=?";

    /***
     * Se sterge o linie din tabelul client cu ajutorul statement-ului de mai sus "deleteStatementString"
     * @param clientName numele clientului care urmeaza sa fie sters
     * @param address adresa clientului care urmeaza sa fie sters
     */
    public static void delete(String clientName,String address) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        //int insertedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, clientName);
            deleteStatement.setString(2, address);
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            // if (rs.next()) {
            //  deletedId = rs.getInt(1);
            // }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DeleteClient:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        // return insertedId;

    }

}
