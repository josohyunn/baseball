package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@ToString
public class Team {
    int teamId; //pk
    int stadiumId; //fk
    String teamName;
    Timestamp teamCreatedAt;

    @Builder
    public Team(int teamId, int stadiumId, String teamName, Timestamp teamCreateAt) {
        this.teamId = teamId;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreateAt;
    }
}
