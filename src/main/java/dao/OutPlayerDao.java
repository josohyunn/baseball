package dao;

import model.OutPlayer;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDao {

    private Connection connection;

    public OutPlayerDao(Connection connection) {
        this.connection = connection;
    }

    // 선수 추가
    public void insertPlayer(int outPlayerId, int playerId, String reason) throws SQLException {
        String query = "INSERT INTO player_tb (id, player_id, reason) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setInt(2, playerId);
            statement.setString(3, reason);
            statement.executeUpdate();
        }
    }

    // 전체 퇴출 선수 조회
    public List<OutPlayer> getAllPlayers() throws SQLException {
        List<OutPlayer> outplayers = new ArrayList<>();
        String query = "SELECT * FROM out_player_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    OutPlayer outplayer = buildOutPlayerFromResultSet(resultSet);
                    outplayers.add(outplayer);
                }
            }
        }
        return outplayers;
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

    private OutPlayer buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int outPlayerId = resultSet.getInt("id");
        int playerId = resultSet.getInt("player_id");
        String reason = resultSet.getString("reason");
        Timestamp outPlayerCreatedAt = resultSet.getTimestamp("created_at");

        return OutPlayer.builder()
                .outPlayerId(outPlayerId)
                .playerId(playerId)
                .reason(reason)
                .outPlayerCreatedAt(outPlayerCreatedAt)
                .build();
    }
}
