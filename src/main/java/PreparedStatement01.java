import java.sql.*;// buradaki * sql importlarinin tamamini almis olarak kabul eder.

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement statement = connection.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //PreparedStatement olusturmak icin
        //1.Adim: PreparedStatement Query olustur. parametrelendirme yap.
        String sql1 = "update companies set number_of_employees = ?  where company= ? "; // ? demek parametrelendirmek demek

        //2.Adim: PreparedStatement objesi olusturuyoruz ve icinde parametrelendirimis query'i icinde calisitiriyoruz
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);

        //3.Adim: PreparedStatement objesi ile setInt() gibi methodlarla soru isaretleri yerlerine koymak istedgiimiz degeleri yerlestiriyoruz.
        preparedStatement.setInt(1,9999);
        preparedStatement.setString(2,"IBM");


        //4.Adim: Qyery'i calistir
        int guncellenenSatirSayisis = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisis = " + guncellenenSatirSayisis);

        //5.Adim: Guncelleme sonrasi yeni table'i okuyalim.
        String sql2 = "select * from companies";
        ResultSet resultSet1 = statement.executeQuery(sql2);
        while (resultSet1.next()){
            System.out.println(resultSet1.getObject(1) + "--" + resultSet1.getObject(2) + "--" + resultSet1.getObject(3));
        }


        //  //2. Örnek: Prepared statement kullanarak company adı CASPER olan number_of_employees değerini 5000 olarak güncelleyin.

        System.out.println("**********************************ornek 2 ********************");



        preparedStatement.setInt(1,5000);
        preparedStatement.setString(2,"CASPER");

        int guncellenenSatirSayisi2 = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        String sql3 = "select * from companies";
        ResultSet resultSet2 = statement.executeQuery(sql3);
        while (resultSet2.next()){
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2) + "--" + resultSet2.getObject(3));
        }







        connection.close();
        statement.close();




    }
}
