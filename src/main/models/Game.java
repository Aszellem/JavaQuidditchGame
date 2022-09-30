package src.main.models;

import java.util.HashMap;

public class Game {

    private HashMap<Team, Integer> scoreboard;
    private static int gameCount;

    public Game(Team home, Team away) {
        this.scoreboard = new HashMap<Team, Integer>();
        this.scoreboard.put(new Team(home), 0);
        this.scoreboard.put(new Team(away), 0);
        gameCount++;
    }

    public static int getGameCount() {
        return gameCount;
    }

    public Integer getScore(Team team){
        return scoreboard.get(team);
    }

    public void setScore(Team team, Integer score){
        scoreboard.put(team, score);   
    }

    public Team getTeam(String name){
        return scoreboard.keySet().stream()
        .filter((key) -> key.getHouse().equals(name))
        .findFirst()
        .orElse(null);
    }    

}
