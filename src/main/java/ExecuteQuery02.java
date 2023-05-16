import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement statement = connection.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        //1.yol ==> offset ve limit kullanarak yaptik

        String sql1 ="select company, number_of_employees from companies order by number_of_employees desc  offset 1 limit 1;";
        ResultSet resultSet = statement.executeQuery(sql1);
        resultSet.next(); // tek veri oldugu icin loopa gerek kalmadi
        System.out.println(resultSet.getObject(1) + "--" + resultSet.getObject(2));

        System.out.println("\n*****************2.ornek*********************\n");

        //2.yol subquery kullanilarak yapilacak
        String sql2 ="select company, number_of_employees from companies where number_of_employees =\n" +
                "(select max(number_of_employees) from companies \n" +
                " where number_of_employees< (select max(number_of_employees) from companies));";

        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2));
        }

        connection.close();
        statement.close();


    }
}
