package app;

import dao.PlayerDao;
import db.DBConnection;
import dto.PlayerDetailDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseBallApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        // DI
        PlayerDao playerDao = new PlayerDao(connection);


        try {
            int teamId = 1;
            List<PlayerDetailDto> dtos = new ArrayList<>();

            System.out.println("선수이름 : ");
            System.out.println(playerDao.getAllPlayers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
