package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getInstance(){
        // MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/baseball";
        String username = "root";
        String password = "zxc123!@#";

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("디버그 : DB연결 성공");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

//teardown까지 포함한 DBConnection
//package db;
//
//        import org.junit.jupiter.api.Test;
//
//        import java.io.BufferedReader;
//        import java.io.FileReader;
//        import java.sql.Connection;
//        import java.sql.PreparedStatement;
//        import java.sql.Statement;
//
//public class DBInitTest {
//
//    @Test
//    public void init_test(){
//        DBInit dbInit = new DBInit();
//        String sql = dbInit.readTeardown();
//
//
//        try {
//            Connection connection = DBConnection.getInstance();
//            Statement statement = connection.createStatement();
//            String[] queries = sql.split(";");
//
//            for (String query : queries) {
//                if (!query.trim().isEmpty()) {
//                    statement.executeUpdate(query);
//                }
//            }
//
//            statement.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
