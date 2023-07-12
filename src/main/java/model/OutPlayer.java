package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString

public class OutPlayer {
    int outPlayerId; //pk
    int playerId; //fk
    String reason;
    Timestamp outPlayerCreatedAt;

    @Builder
    public OutPlayer(int outPlayerId, int playerId, String reason, Timestamp outPlayerCreatedAt) {
        this.outPlayerId = outPlayerId;
        this.playerId = playerId;
        this.reason = reason;
        this.outPlayerCreatedAt = outPlayerCreatedAt;
    }

}
