package dao;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    private Connection connection;

    public PlayerDao(Connection connection) {
        this.connection = connection;
    }

    // 선수 추가
    public int insertPlayer(int teamId, String playerName, String playerPosition) throws SQLException {
        String query = "INSERT INTO player_tb (team_id, player_name, player_position, player_created_at) VALUES (?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setInt(1, playerId);
            statement.setInt(1, teamId);
            statement.setString(2, playerName);
            statement.setString(3, playerPosition);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            return -1; // 중복선수 존재
        }
    }


    // 전체 선수 조회
    public List<Player> getAllPlayers(int teamId) throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player_tb WHERE team_id = ? ORDER BY player_id ASC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Player player = buildPlayerFromResultSet(resultSet);
                    players.add(player);
                }
            }
        }
        return players;
    }

     //선수 퇴출
    public void deletePlayer(int playerId) throws SQLException {
        String query = "UPDATE player_tb SET team_id=null WHERE player_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.executeUpdate();
        }
    }

    private Player buildPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int playerId = resultSet.getInt("player_id");
        int teamId = resultSet.getInt("team_id");
        String playerName = resultSet.getString("player_name");
        String playerPosition = resultSet.getString("player_position");
        Timestamp playerCreatedAt = resultSet.getTimestamp("player_created_at");


        return Player.builder()
                .playerId(playerId)
                .teamId(teamId)
                .playerName(playerName)
                .playerPosition(playerPosition)
                .playerCreatedAt(playerCreatedAt)
                .build();
    }
}
