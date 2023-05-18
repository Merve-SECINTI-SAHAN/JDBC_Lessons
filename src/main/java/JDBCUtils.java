import java.sql.*;

public class JDBCUtils {
    // Bu class'ta tekrarli kullanilacak methodlar olusturacagiz.
    private static Connection connection;
    private static Statement statement;

    private static ResultSet resultSet;


    // Database'e baglanma methoud==> connetction retunr eder.
    public static Connection connectToDataBase(){

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    // Medunna Databas'e baglanan method
    public static Connection connectToMedunnaDataBase(){

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // Statement olusturma methodu:

    public static Statement createStatement (){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    // execute() methodu ile query calistiran method

    public static boolean execute(String sql){
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //executeQuery ile query calistiran method
    public static ResultSet executeQuery(String sql){
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    //baglantiyi kapatan method
    public static void closeConnection(){
        try {
            connection.close();
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
