package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.OutPlayer;
import model.Player;
import model.Stadium;
import model.Team;

import java.sql.Timestamp;

@Getter
@Setter
public class OutPlayerRespDto { //player outer join out_player

    // player_tb
    private int playerId; //pk
    private String playerName;
    private String playerPosition;

    // out_player_tb
    private String outPlayerReason;
    private Timestamp outPlayerCreatedAt;

    @Builder
    public OutPlayerRespDto(int playerId, String playerName, String playerPosition, String outPlayerReason, Timestamp outPlayerCreatedAt) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.outPlayerReason = outPlayerReason;
        this.outPlayerCreatedAt = outPlayerCreatedAt;

    }



}
