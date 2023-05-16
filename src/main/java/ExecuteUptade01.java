import java.sql.*;

public class ExecuteUptade01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement statement = connection.createStatement();

        ////1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql = "update companies set number_of_employees =16000 \n" +
                "where number_of_employees <( select avg(number_of_employees ) from companies);";
        int result = statement.executeUpdate(sql); // executeUpdate () methodu update edilen satir sayisini int deger olarak dondurur

        System.out.println(result); // update edilen satir sayisi =2

        System.out.println("************update sonrasi*****************");

        // Update sonrsi datayi okkuma icin SQL(select) kullaniyoruz.
        String sql2 = "select * from companies";
        ResultSet resultset = statement.executeQuery(sql2);
        while (resultset.next()){
            System.out.println(resultset.getObject(1) + "--" + resultset.getObject(2) + "--" + resultset.getObject(3));
        }


        connection.close();
        statement.close();
    }


}
