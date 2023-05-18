import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement statement = connection.createStatement();
        //1.ORnek:  Selamlama yapan bir function olusturun. callable statement ile cagiriniz.
        // Callable statement adimlari :
        //1.Adim: Function kodunu yaziniz
        String sql = "create or replace function selamlama(x text) returns  text as $$ begin return 'Merhaba ' " +
                "|| x || ', nasilsin?'; end; $$ language plpgsql;";

        //2.Adim: Function kodunu calistiriniz
        statement.execute(sql); // Function olusturan query'yi calistirdik.

       // String sql2 = "select selamlama ('Ahmet');"; //==> Burasi callable statement kullanmadan cagirma islemi ======>
       // ResultSet resultSet = statement.executeQuery(sql2); // Function'i parametre ile cagirdik. Bize bir table dondurdu.
       // resultSet.next();
       // System.out.println(resultSet.getObject(1));

        // 1.Ornek: Selamlama yapan bir function'i callable statement ile cagirin.

        //3.Adim: Function'u cagirma
        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");

        //4.Adim: Return icin bir registerOutParameter() methodunu, parametreler icin setInt(), setString() ... methodlarini kullaniniz.

        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, "ayse");

        // 5. Adim: execute() methodu ile callableStatement'i calisitiyoruz. boolean dondururz ama icine data gelir sonra okuma yapmamiz gerekir.

        callableStatement.execute();

        //6,Adim: Sonucu gormek icin callableStatement icindeki datayi okuyoruz
        // callableStatement'ta data resultset icine alinmaz. Direk callableStatement'tan alinir.
        System.out.println(callableStatement.getString(1));



        // 2, Ornek: Iki sayiyi toplayan bir function yaziniz ve callable statement ile cagiriniz.

        //1.Adim: Function kodunu yaziniz
        String sql2 = " create or replace  function toplama (x int, y int) returns numeric as $$ begin return  x + y; end; $$ language plpgsql;  ";

        //2.Adim: Function kodunu calistiriniz
        statement.execute(sql2); // Function olusturan query'yi calistirdik.

        //3.Adim: Function'u cagirma
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?,?)}");

        //4.Adim: Return icin bir registerOutParameter() methodunu, parametreler icin setInt(), setString() ... methodlarini kullaniniz.

        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2, 4);
        callableStatement2.setInt(3, 6);

        // 5. Adim: execute() methodu ile callableStatement'i calisitiyoruz. boolean dondururz ama icine data gelir sonra okuma yapmamiz gerekir.

        callableStatement2.execute();

        //6,Adim: Sonucu gormek icin callableStatement icindeki datayi okuyoruz
        // callableStatement'ta data resultset icine alinmaz. Direk callableStatement'tan alinir.
        System.out.println(callableStatement2.getBigDecimal(1));










































        connection.close();
        statement.close();

    }

    public void toplama (int x, int y){
        System.out.println(x+y);
    }
}
