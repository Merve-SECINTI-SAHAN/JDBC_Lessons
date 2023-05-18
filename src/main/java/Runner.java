public class Runner {
    public static void main(String[] args) {
        // Databese'e baglan

        JDBCUtils.connectToDataBase();

        //Statement olustur
        JDBCUtils.createStatement();

        // query calistir.
        String sql = "CREATE TABLE workers(worker_id varchar(20), worker_name varchar(20), worker_salary int);";
        JDBCUtils.execute(sql);


    }
}
