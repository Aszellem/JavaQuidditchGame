package src.main.models;

import java.util.HashMap;

public class Game {

    private HashMap<Team, Integer> scoreboard;
    private static int gameCount;
    private static final int QUAFFLE_POINTS = 10;
    private static final int SNITCH_POINT = 150;

    public Game(Team home, Team away) {
        this.scoreboard = new HashMap<Team, Integer>();
        this.scoreboard.put(new Team(home), 0);
        this.scoreboard.put(new Team(away), 0);
        gameCount++;
    }

    public static int getQuafflePoints() {
        return QUAFFLE_POINTS;
    }

    public static int getSnitchPoints() {
        return SNITCH_POINT;
    }

    public static int getGameCount() {
        return gameCount;
    }

    public Integer getScore(Team team) {
        return scoreboard.get(team);
    }

    public void setScore(Team team, Integer score) {
        if(team == null){
            throw new IllegalArgumentException("Cannot add null to the scoreboard");
        }
        scoreboard.put(team, score);
    }

    public Team getTeam(String name) {
        return scoreboard.keySet().stream()
                .filter((key) -> key.getHouse().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String getPlaceholder(String play) {
        return play.substring(play.indexOf("<") + 1, play.indexOf(">"));
    }

    public String replacePlaceholder(String play, String placeholder, String value) {
        return play.replace("<" + placeholder + ">", value);
    }

    public void quaffleScore(Team team) {
        setScore(team, getScore(team) + QUAFFLE_POINTS);
    }

    public void catchSnitch(Team team) {
        setScore(team, getScore(team) + SNITCH_POINT);
    }

    public String simulate(String play){
        String placeholer = getPlaceholder(play);
        Team team = getRandomTeam();
        String value = "";
        if(placeholer.equals(Team.getPositionChaser())){
            quaffleScore(team);
            value = team.getChasers()[random(team.getChasers().length)];
        }else if (placeholer.equals(Team.getPositionSeeker())){
            catchSnitch(team);
            value = team.getSeeker();
        }else if (placeholer.equals(Team.getPositionKeeper())){
            value = team.getKeeper();
        }
            return replacePlaceholder(play, placeholer, value);
    }

    public Team getRandomTeam(){
        Object[] teams = scoreboard.keySet().toArray();
        return (Team)teams[random(teams.length)];
    }

    public int random(int range){
        return (int) (Math.random() * range - 1);
    }
}
