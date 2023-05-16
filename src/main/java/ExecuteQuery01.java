import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement statement = connection.createStatement();

        //1.Ornek:
        String sql1 = "select country_name from countries where region_id=1;";
        boolean result1 = statement.execute(sql1);
        System.out.println("result1 = " + result1); // true==> DQL ile data dondu.

        //Satirlari gormek icin executeQuery metodunu kullanmaliyiz. execute() methodu sadece tru veya false doner.

        ResultSet resultSet = statement.executeQuery(sql1);
        while ( resultSet.next()){
            System.out.println(resultSet.getString("country_name"));
        }
        System.out.println("\n********************2.ornek *********************\n");
        //-- 2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "select country_id, country_name from countries where region_id>2";
        ResultSet resultset2 = statement.executeQuery(sql2);
        resultset2.next();
        System.out.println(resultset2.getString(2));

        while (resultset2.next()){ //resultset son satira geldikten sonra kapanir ve dolayisi ile false verir. Kapali resultset uzerinde islem yapilirsa exeption atar.
            System.out.println(resultset2.getString(1)+ "--" + resultset2.getString(2));
        }

        System.out.println("\n********************3.ornek *********************\n");

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3 = "select * from companies where number_of_employees =(select min(number_of_employees) from companies)";
        ResultSet resultset3 = statement.executeQuery(sql3);

        while (resultset3.next()){
            System.out.println(resultset3.getObject(1) + "--" + resultset3.getObject(2) + "--" + resultset3.getObject(3));
        }
    }
}
