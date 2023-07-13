package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.Stadium;
import model.Team;

import java.sql.Timestamp;

@Getter  @Setter
public class TeamRespDto { //전체 팀 목록(Stadium 정보를 조인해서 출력해야됨)
    private  int teamId; //pk
    private String teamName;
    private Timestamp teamCreatedAt;
    private Stadium stadium;

    @Builder //int stadiumId, String stadiumName, Timestamp stadiumCreatedA
    public TeamRespDto(Team team, Stadium stadium) {
        teamId = team.getTeamId();
        this.stadium = new Stadium(stadium.getStadiumId(), stadium.getStadiumName(), stadium.getStadiumCreatedAt());
        teamName = team.getTeamName();
        teamCreatedAt = team.getTeamCreatedAt();

    }

}
