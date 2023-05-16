import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. Adim: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adim:
       Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");

       //3. adim: Statement olustur


        Statement statement = connection.createStatement();

        //4.Adim: Query calistir.
        /*
        1) Eger execute () methodu DDL (create, drop, alter table) ile ile kullanilirsa her zaman 'false' doner.
        2) Eger execute () methodu DQL (select) ile ile kullanilirsa data donerse 'ture', data donmezse 'false' doner.
         */

        // execute() metodu parametre olarak girilen String SQL komutu

        //CREATE TABLE workers(worker_id varchar(20), worker_name varchar(20), worker_salary int);
        // 1. Ornek: workers adinda bir table olusturunuz.
        boolean sql1 = statement.execute("CREATE TABLE workers(worker_id varchar(20), worker_name varchar(20), worker_salary int);");
        System.out.println("sql1 = " + sql1);

        //2.Ornek: workers table'ina worker_address sutunu ekleyiniz

        String sqlQuery1 = "alter TABLE workers add worker_address varchar(100);";
        boolean sql2 = statement.execute(sqlQuery1);
        System.out.println("sql1 = " + sql2);

        //3. Ornek Worker table'i siliniz.

        String sqlQuery2 = "drop table workers;";

        boolean sql3 = statement.execute(sqlQuery2);
        System.out.println("sql3 = " + sql3);


        //5. Adim:  Baglantiyi kapar
        connection.close();
        statement.close();


    }
}
