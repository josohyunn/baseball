package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDao {

    private Connection connection;

    public StadiumDao(Connection connection) {
        this.connection = connection;
    }

    // 야구장 생성
    public void createStadium(int stadiumId, String stadiumName) throws SQLException {
        String query = "INSERT INTO stadium_tb (id, name, create_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, stadiumName);
            statement.executeUpdate();
        }
    }

    // 야구장 수정
    public void updateStadium(int stadiumId, String stadiumName) throws SQLException {
        String query = "UPDATE stadium_tb SET name = ?  WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, stadiumName);
            statement.setInt(2, stadiumId);
            statement.executeUpdate();
        }
    }

    // 야구장 삭제
    public void deleteStadium(int stadiumId) throws SQLException {
        String query = "DELETE FROM stadium_tb WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.executeUpdate();
        }
    }

//    private Account buildAccountFromResultSet(ResultSet resultSet) throws SQLException {
//        int accountNumber = resultSet.getInt("account_number");
//        String accountPassword = resultSet.getString("account_password");
//        int accountBalance = resultSet.getInt("account_balance");
//        Timestamp accountCreatedAt = resultSet.getTimestamp("account_created_at");
//
//        return Account.builder()
//                .accountNumber(accountNumber)
//                .accountPassword(accountPassword)
//                .accountBalance(accountBalance)
//                .accountCreatedAt(accountCreatedAt)
//                .build();
//    }
}
