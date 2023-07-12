package dao;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private Connection connection;

    public TeamDao(Connection connection) {
        this.connection = connection;
    }

    // 팀 추가
    //    int teamId; //pk
    //    int stadiumId; //fk
    //    String teamName;
    //    Timestamp teamCreatedAt;
    // 데이터베이스 : id  stadium_tb  name   created_at
    public void insertTeam(int teamId, int stadiumId, String teamName) throws SQLException {
        String query = "INSERT INTO team_tb (id, stadium_tb, name) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            statement.setInt(2, stadiumId);
            statement.setString(3, teamName);
            statement.executeUpdate();
        }
    }

    // 전체 팀 조회
    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Player player = buildPlayerFromResultSet(resultSet);
                    players.add(player);
                }
            }
        }
        return players;
    }

    // 선수 정보 수정
//    public void updatePlayer(int playerId, int teamId, String playerName, String playerPosition) throws SQLException {
//        String query = "UPDATE player_tb SET team_id = ?, name = ?, position = ? WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, teamId);
//            statement.setString(2, playerName);
//            statement.setString(3, playerPosition);
//            statement.setInt(4, playerId);
//            statement.executeUpdate();
//        }
//    }

    // 선수 퇴출
//    public void deletePlayer(int playerId) throws SQLException {
//        String query = "DELETE FROM player_tb WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, playerId);
//            statement.executeUpdate();
//        }
//    }

    private Player buildPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int playerId = resultSet.getInt("id");
        int teamId = resultSet.getInt("team_id");
        String playerName = resultSet.getString("name");
        String playerPosition = resultSet.getString("position");
        Timestamp playerCreateAt = resultSet.getTimestamp("created_at");


        return Player.builder()
                .playerId(playerId)
                .teamId(teamId)
                .playerName(playerName)
                .playerPosition(playerPosition)
                .playerCreateAt(playerCreateAt)
                .build();
    }
}
