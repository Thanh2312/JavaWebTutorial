package DAL;

import java.sql.*;

public class ConnectDB {
    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private static String db_url = "jdbc:sqlserver://localhost;databaseName = ManageCar; user = sa;password=sa";

    public void DataHandle(String query){
        try{
            conn = getConnection(db_url);
            statement = conn.createStatement();
            statement.execute(query);
//            preparedStatement.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Cannot connect database, " + e);
        }
        finally {
            try {
                conn.close();
                statement.close();
            }
            catch (SQLException x){
                x.printStackTrace();
                System.err.println("Cannot close database, " + x);
            }
        }
    }

    public ResultSet GetTable(String query) {
        try{
            conn = getConnection(db_url);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
            System.err.println("Cannot connect database" + e);
        }
        finally {
            try{
                conn.close();
                statement.close();
            }
            catch(SQLException x){
                x.printStackTrace();
                System.err.println("Cannot close database" + x);
            }
            return rs;
        }
    }


    public static Connection getConnection (String db_url){
        Connection conn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(db_url);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
