package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@ToString
public class Stadium {
    private int stadiumId; //pk
    private String stadiumName;
    private Timestamp stadiumCreatedAt;

    @Builder
    public Stadium(int stadiumId, String stadiumName, Timestamp stadiumCreatedAt) {
        this.stadiumId = stadiumId;
        this.stadiumName = stadiumName;
        this.stadiumCreatedAt = stadiumCreatedAt;
    }
}
