package dao;

import dto.TeamRespDto;
import model.Player;
import model.Stadium;
import model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private Connection connection;

    public TeamDao(Connection connection) {
        this.connection = connection;
    }


    public void insertTeam(int stadiumId, String teamName) throws SQLException {
        String query = "INSERT INTO team_tb (stadium_id, team_name, team_created_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setInt(1, teamId);
            statement.setInt(1, stadiumId);
            statement.setString(2, teamName);
            statement.executeUpdate();
        }
    }

    // 전체 팀 조회
    public List<TeamRespDto> getAllTeams() throws SQLException {
        List<TeamRespDto> teamDtos = new ArrayList<>();
        String query = "SELECT team_id, team_name, team_created_at, stadium_tb.stadium_id, stadium_name, stadium_created_at \n" +
                "FROM team_tb left join stadium_tb on team_tb.stadium_id = stadium_tb.stadium_id;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    // Team 정보
                    int teamId = resultSet.getInt("team_id");
                    String teamName = resultSet.getString("team_name");
                    Timestamp teamCreatedAt = resultSet.getTimestamp("team_created_at");

                    // Stadium 정보
                    int stadiumId = resultSet.getInt("stadium_id");
                    String stadiumName = resultSet.getString("stadium_name");
                    Timestamp stadiumCreatedAt = resultSet.getTimestamp("stadium_created_at");

                    Team team = new Team(teamId, stadiumId, teamName, teamCreatedAt);
                    Stadium stadium = new Stadium(stadiumId, stadiumName, stadiumCreatedAt);
                    teamDtos.add(new TeamRespDto(team, stadium));
                }
            }
        }
        return teamDtos;
    }


     //트랜잭션 남기기
    private Team buildTeamFromResultSet(ResultSet resultSet) throws SQLException {
        int teamId = resultSet.getInt("team_id");
        int stadiumId = resultSet.getInt("stadium_id");
        String teamName = resultSet.getString("team_name");
        Timestamp teamCreatedAt = resultSet.getTimestamp("team_created_at");


        return Team.builder()
                .teamId(teamId)
                .stadiumId(stadiumId)
                .teamName(teamName)
                .teamCreatedAt(teamCreatedAt)
                .build();
    }

}
