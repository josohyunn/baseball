package dao;

import dto.OutPlayerRespDto;
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
            statement.setInt(1, playerId);
            statement.setString(2, outPlayerReason);
            statement.executeUpdate();
        } catch (Exception e){

        }
    }

    // 전체 퇴출 선수 조회
    public List<OutPlayerRespDto> getAllOutPlayers() throws SQLException {
        List<OutPlayerRespDto> outplayers = new ArrayList<>();
        String query = "SELECT player_tb.player_id, player_name, player_position, out_player_reason, out_player_created_at " +
                "FROM player_tb left JOIN out_player_tb on player_tb.player_id = out_player_tb.player_id;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {

                    int playerId = resultSet.getInt("player_tb.player_id");
                    String playerName = resultSet.getString("player_name");
                    String playerPosition = resultSet.getString("player_position");

                    String outPlayerReason = resultSet.getString("out_player_reason");
                    Timestamp outPlayerCreatedAt = resultSet.getTimestamp("out_player_created_at");

                    outplayers.add(new OutPlayerRespDto(playerId, playerName, playerPosition, outPlayerReason, outPlayerCreatedAt));
                }
            }
        }
        return outplayers;
    }


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
