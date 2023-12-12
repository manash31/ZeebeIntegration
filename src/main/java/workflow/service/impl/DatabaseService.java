package workflow.service.impl;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService implements DBService{


    @Override
    public Connection establishConnection() throws SQLException {

        String databaseName = "postgre";
        String dburl = "";
        String user = "";
        String password = "";

        Connection connection = null;
        try{
            connection = DriverManager.getConnection(dburl,user,password);
        }
        catch (Exception e){}
        return connection;
    }
}