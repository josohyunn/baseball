package dao;

import model.Player;
import model.Stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDao {

    private Connection connection;

    public StadiumDao(Connection connection) {
        this.connection = connection;
    }

    // 야구장 생성
    public void createStadium(String stadiumName) throws SQLException {
        String query = "INSERT INTO stadium_tb (stadium_name, stadium_created_at) VALUES (?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setInt(1, stadiumId);
            statement.setString(1, stadiumName);
            statement.executeUpdate();
        }
    }

    // 전체 야구장 목록보기
    public List<Stadium> getAllStadiums() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Stadium stadium = buildStadiumFromResultSet(resultSet);
                    stadiums.add(stadium);
                }
            }
        }
        return stadiums;
    }

    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException {
        int stadiumId = resultSet.getInt("stadium_id");
        String stadiumName = resultSet.getString("stadium_name");
        Timestamp stadiumCreatedAt = resultSet.getTimestamp("stadium_created_at");

        return Stadium.builder()
                .stadiumId(stadiumId)
                .stadiumName(stadiumName)
                .stadiumCreatedAt(stadiumCreatedAt)
                .build();
    }
}
