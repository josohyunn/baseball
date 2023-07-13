package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString

public class OutPlayer {
    private int outPlayerId; //pk
    private int playerId; //fk
    private String outPlayerReason;
    private Timestamp outPlayerCreatedAt;

    @Builder
    public OutPlayer(int outPlayerId, int playerId, String outPlayerReason, Timestamp outPlayerCreatedAt) {
        this.outPlayerId = outPlayerId;
        this.playerId = playerId;
        this.outPlayerReason = outPlayerReason;
        this.outPlayerCreatedAt = outPlayerCreatedAt;
    }

}
