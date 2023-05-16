import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement statement = connection.createStatement();

        //1.Ornek:
        String sql1 = "select country_name from countries where region_id=1;";
        boolean result1 = statement.execute(sql1);
        System.out.println("result1 = " + result1);

        //Satirlari gormek icin executeQuery metodunu kullanmaliyiz. execute() methodu sadece tru veya false doner.

        ResultSet resultSet = statement.executeQuery(sql1);
        while ( resultSet.next()){
            System.out.println(resultSet.getString("country_name"));
        }

    }
}
