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

    // 퇴출 선수 추가
    public void insertOutPlayer(int playerId, String outPlayerReason) throws SQLException {
        String query = "INSERT INTO out_player_tb (player_id, out_player_reason, out_player_created_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setInt(1, playerId);
            statement.setInt(1, playerId);
            statement.setString(2, outPlayerReason);
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

    // 선수 퇴출
//    public void deletePlayer(int playerId) throws SQLException {
//        String query = "DELETE FROM player_tb WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, playerId);
//            statement.executeUpdate();
//        }
//    }

    private OutPlayer buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int outPlayerId = resultSet.getInt("out_player_id");
        int playerId = resultSet.getInt("player_id");
        String outPlayerReason = resultSet.getString("out_player_reason");
        Timestamp outPlayerCreatedAt = resultSet.getTimestamp("out_player_created_at");

        return OutPlayer.builder()
                .outPlayerId(outPlayerId)
                .playerId(playerId)
                .outPlayerReason(outPlayerReason)
                .outPlayerCreatedAt(outPlayerCreatedAt)
                .build();
    }
}
