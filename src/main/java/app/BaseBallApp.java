package app;

//import dao.PlayerDao;

import dao.OutPlayerDao;
import dao.PlayerDao;
import dao.StadiumDao;
import dao.TeamDao;
import db.DBConnection;
import dto.OutPlayerRespDto;
import dto.TeamRespDto;
import model.OutPlayer;
import model.Player;
import model.Stadium;
import model.Team;
import util.MyStringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        // DI
        PlayerDao playerDao = new PlayerDao(connection);
        StadiumDao stadiumDao = new StadiumDao(connection);
        TeamDao teamDao = new TeamDao(connection);
        OutPlayerDao outPlayerDao = new OutPlayerDao(connection);



        boolean bool = true;

        while (true) {
            System.out.println("==================================");
            System.out.println("1. 야구장 등록");
            System.out.println("2. 전체 야구장 목록 보기");
            System.out.println("3. 팀 등록");
            System.out.println("4. 전체 팀 목록");
            System.out.println("5. 선수 등록");
            System.out.println("6. 팀별 선수 목록");
            System.out.println("7. 선수 퇴출 등록");
            System.out.println("8. 선수 퇴출 목록");
            System.out.println("9. 포지션별 팀 야구 선수 페이지");
            System.out.println("0. 종료");
            System.out.println("==================================");
            System.out.print("실행할 기능을 입력하세요: ");

            Scanner sc = new Scanner(System.in);
            String request = sc.nextLine();
            String[] parts = request.split("[?]");
            if (parts[0].equals("야구장등록")) { // 1. 야구장 등록
                String[] bodyParts = parts[1].split("=");
                try {
                    stadiumDao.createStadium(bodyParts[1]);
                    System.out.println("야구장 등록 성공");
                    bool=false;
                } catch (SQLException e) {
                    System.out.println("야구장 등록 실패");
                }

            } else if (parts[0].equals("야구장목록")) { // 2. 전체 야구장 목록 보기
                List<Stadium> dtos = null;
                try {
                    dtos = stadiumDao.getAllStadiums();
                    dtos.forEach(dto -> {
                        System.out.println(dto.getStadiumId()+") " +dto.getStadiumName());
                        System.out.println("   개장 날짜: "+ MyStringUtils.dateFormat(dto.getStadiumCreatedAt()));
                        System.out.println("");
                    });
                    bool=false;
                } catch (SQLException e) {
                    System.out.println("야구장 목록 불러오기 실패");
                }

                //  팀등록?stadiumId=1&name=NC
            } else if (parts[0].equals("팀등록")) { // 3. 팀 등록
                String[] bodyParts = parts[1].split("&");
                String[] stadiumParts = bodyParts[0].split("=");
                String[] nameParts = bodyParts[1].split("=");
                String teamName = nameParts[1];
                int stadiumId = Integer.parseInt(stadiumParts[1]);
                try {
                    teamDao.insertTeam(stadiumId, teamName);
                    System.out.println("팀 등록이 완료되었습니다.");
                } catch (SQLException e) {
                    System.out.println("팀 등록 실패");
                } finally {

                }

            } else if(parts[0].equals("팀목록")) { // 4. 전체 팀 목록 Stadium 정보를 조인해서 출력하기(TeamRespDTO 필요)
                List<TeamRespDto> dtos = null;
                try {
                    dtos = teamDao.getAllTeams();
                    dtos.forEach(dto -> {
                        System.out.println(dto.getTeamId() + ") " +dto.getTeamName());
                        System.out.println("   생성일자: " + MyStringUtils.dateFormat(dto.getTeamCreatedAt()));
                        System.out.println("   (" + dto.getStadium().getStadiumId() + "번)경기장: " + dto.getStadium().getStadiumName());
                        System.out.println("");
                    });
                } catch (SQLException e) {
                    System.out.println("팀 목록 불러오기 실패");
                }

            } else if(parts[0].equals("선수등록")) { // 5. 선수 등록
                List<Player> dtos = null;
                String[] bodyParts = parts[1].split("&");
                String[] teamParts = bodyParts[0].split("=");
                String[] nameParts = bodyParts[1].split("=");
                String[] positionParts = bodyParts[2].split("=");
                int teamId = Integer.parseInt(teamParts[1]);
                String playerName = nameParts[1];
                String playerPosition = positionParts[1];
                try {
                    if(playerDao.insertPlayer(teamId, playerName, playerPosition) == 1)
                        System.out.println("선수 등록이 완료되었습니다.");
                    else {
                        System.out.println("이미 존재하는 선수입니다.");
                    }
                } catch (SQLException e) {
                    System.out.println("선수 등록 실패");
                }

            } else if(parts[0].equals("선수목록")) { // 6. 팀별 선수 목록(team_id는 출력하지 않아도 된다)
                List<Player> dtos = null;
                String[] bodyParts = parts[1].split("=");
                System.out.println("---------" + Integer.parseInt(bodyParts[1])+ "팀 선수 목록" + "---------");
                try {
                    dtos = playerDao.getAllPlayers(Integer.parseInt(bodyParts[1]));
                    dtos.forEach(dto -> {
                        if(dto.getTeamId() == Integer.parseInt(bodyParts[1])) {
                            System.out.println(dto.getPlayerId() + "번: " + dto.getPlayerName());
                            System.out.println("포지션: " + dto.getPlayerPosition());
                            System.out.println("입단 날짜: " + MyStringUtils.dateFormat(dto.getPlayerCreatedAt()));
                            System.out.println("");
                        }
                    });
                } catch (Exception e) {
                    //System.out.println("선수 목록 불러오기 실패");
                    e.printStackTrace();
                }

            } else if(parts[0].equals("퇴출등록")) { // 7. 선수 퇴출 등록(두 개 이상의 write문이 실행되야 한다.
                // 이 때는 반드시 트랜잭션 관리가 Service에서 필요하다.
                // out_player에 퇴출 선수를 insert하고, player 테이블에서 해당 선수의 team_id를 null로 변경한다.
                String[] bodyParts = parts[1].split("&");
                String[] playerParts = bodyParts[0].split("=");
                String[] reasonParts = bodyParts[1].split("=");
                int playerId = Integer.parseInt(playerParts[1]);
                String outPlayerReason = reasonParts[1];

                try {
                    outPlayerDao.insertOutPlayer(playerId, outPlayerReason);
                    playerDao.deletePlayer(playerId);
                    System.out.println("퇴출 선수 등록이 완료되었습니다.");
                } catch (SQLException e) {
                    System.out.println("퇴출 선수 등록 실패");
                } finally {

                }

            } else if(parts[0].equals("퇴출목록")) { // 8. 선수 퇴출 목록(OutPlayerRespDTO에 담아서 출력한다)
                List<OutPlayerRespDto> dtos = null;
                System.out.println("-------------선수 퇴출 목록-------------");
                try {
                    dtos = outPlayerDao.getAllOutPlayers();
                    dtos.forEach(dto -> {
                        System.out.println(dto.getPlayerId()+"번: " + dto.getPlayerName() +
                                ", 포지션: " + dto.getPlayerPosition());
                        System.out.println("퇴출이유 : " + dto.getOutPlayerReason() +
                                ", 퇴출날짜: " + dto.getOutPlayerCreatedAt());
                    });
                } catch (Exception e) {
                    System.out.println("선수 목록 불러오기 실패");
                }
            } else if(parts[0].equals("포지션별목록")) { // 9. 포지션별 팀 야구 선수 페이지(PositionRespDTO에 값을 담아서 콘솔에 출력한다.)

            } else if(request.startsWith("종료")) { // 0. 종료
                bool=false;
                break;
            }
            else {
                System.out.println("입력 형식에 맞게 입력해주세요.");
            }

        }
    }
}
