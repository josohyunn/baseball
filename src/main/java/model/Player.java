package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Player { // teamId와 playerPosition은 다중 칼럼 유니크 제약조건이 필요
    private int playerId; //pk
    private int teamId; //fk
    private String playerName;
    private String playerPosition;
    private Timestamp playerCreatedAt;

    @Builder
    public Player(int playerId, int teamId, String playerName, String playerPosition, Timestamp playerCreatedAt) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.playerCreatedAt = playerCreatedAt;
    }

}
