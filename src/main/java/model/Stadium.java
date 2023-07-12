package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@ToString
public class Stadium {
    int stadiumId; //pk
    String stadiumName;
    Timestamp stadiumCreatedAt;

    @Builder
    public Stadium(int stadiumId, String stadiumName, Timestamp stadiumCreateAt) {
        this.stadiumId = stadiumId;
        this.stadiumName = stadiumName;
        this.stadiumCreatedAt = stadiumCreateAt;
    }
}
