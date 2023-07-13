package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@ToString
public class Team {
    private int teamId; //pk
    private int stadiumId; //fk
    private String teamName;
    private Timestamp teamCreatedAt;

    @Builder
    public Team(int teamId, int stadiumId, String teamName, Timestamp teamCreatedAt) {
        this.teamId = teamId;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }
}
