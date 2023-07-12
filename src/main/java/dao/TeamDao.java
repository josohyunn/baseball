package dao;

import model.Player;
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
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Team team = buildTeamFromResultSet(resultSet);
                    teams.add(team);
                }
            }
        }
        return teams;
    }


    // 트랜잭션 남기기
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
