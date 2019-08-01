package DAL;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDB {
    private Connection conn;
    private Statement statement;

    public static String db_url = "jdbc:sqlserver://localhost;databaseName = ManageCar; user = nickf2k;password=nickf2k;";

    public void dataHandle(String query){ //thực thi các lệnh update, insert, khả năng cao là lỗi ở đây, vì nếu lỗi ở dòng query thì đã báo lỗi syntax
        try{
            conn = getConnection(db_url);
            statement = conn.createStatement();
            statement.execute(query);
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

    public ArrayList<ArrayList<byte[]>> getResultQuery(String query){
        ArrayList<ArrayList<byte[]>> tableResult = new ArrayList<>();
        ArrayList<byte[]> row;
        conn = getConnection(db_url);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int countColumn = resultSet.getMetaData().getColumnCount();
            if (countColumn==0) return null;
            while (resultSet.next()){
                row = new ArrayList<>();
                for (int i = 0; i<countColumn; i++){
                    row.add(i,resultSet.getBytes(i+1));
                }
                tableResult.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableResult;
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
