package connection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String Dburl = "jdbc:mysql://localhost:3306/tabtema_3";
    private static final String User = "root";
    private static final String Pass = "romeo";

    private static  ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory(){
        try{
            Class.forName(Driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /***
     * @Author: Lazarciuc Romeo
     *  Creează o conexiune cu identitatea utilizatorului implicită.
     *  Conexiunea este creată în modul oprit. Niciun mesaj nu va fi livrat până când Connection.startmetoda nu este chemată în mod explicit.
     * @return o conexiune  nou creeata
     */
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Dburl, User, Pass);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /***
     *
     * @return conexiunea
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }


    public static void close(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    public static void close(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    }


