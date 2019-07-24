package DAL;

import java.sql.*;

public class ConnectDB {
    private String connStr = "jdbc:sqlserver://localhost;databaseName=ManageCar";
    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void DataHandle(String query){
        try{
            conn = DriverManager.getConnection(connStr, "sa", "sa");
            statement = conn.createStatement();
//            statement.execute(query);
            preparedStatement.executeQuery(query);
        }
        catch (SQLException e) {
            System.err.println("Cannot connect database, " + e);
        }
        finally {
            try {
                conn.close();
                statement.close();
            }
            catch (SQLException x){
                System.err.println("Cannot close database, " + x);
            }
        }
    }

    public ResultSet GetTable(String query) {
        try{
            conn = DriverManager.getConnection(connStr, "sa","sa");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
        }
        catch (SQLException e){
            System.err.println("Cannot connect database" + e);
        }
        finally {
            try{
                conn.close();
                statement.close();
            }
            catch(SQLException x){
                System.err.println("Cannot close database" + x);
            }
            return rs;
        }
    }
}
