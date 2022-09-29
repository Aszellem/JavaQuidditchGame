package src.main.models;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class Team {
    private String house;
    private String keeper;
    private String seeker;
    private String[] chasers;

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

    public Team(String house, String keeper, String seeker, String[] chasers) {
        if (house == null || keeper == null || seeker == null) {
            throw new IllegalArgumentException("field values cannot be null");
        }
        if (house.isBlank() || keeper.isBlank() || seeker.isBlank()) {
            throw new IllegalArgumentException("field values cannot be blank");
        }
        if (chasers.length != 3) {
            throw new IllegalArgumentException("must have 3 chasers");
        }
        if (hasNull(chasers) || hasBlank(chasers)) {
            throw new IllegalArgumentException("Illegal elements");
        }
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public static boolean hasBlank(String[] chasers2) {
        for (int i = 0; i < chasers2.length; i++) {
            if (chasers2[i].isBlank()) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNull(String[] chasers2) {
        for (int i = 0; i < chasers2.length; i++) {
            if (chasers2[i] == null) {
                return true;
            }
        }
        return false;
    }

    public Team(Team source) {
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        if (house == null || house.isBlank()) {
            throw new IllegalArgumentException(house + " cannot be null or blank");
        }
        this.house = house;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        if (keeper == null || keeper.isBlank()) {
            throw new IllegalArgumentException(keeper + " cannot be null or blank");
        }
        this.keeper = keeper;
    }

    public String getSeeker() {
        return seeker;
    }

    public void setSeeker(String seeker) {
        if (seeker == null || seeker.isBlank()) {
            throw new IllegalArgumentException(seeker + " cannot be null or blank");
        }
        this.seeker = seeker;
    }

    public String[] getChasers() {
        return Arrays.copyOf(chasers, chasers.length);
    }

    public void setChasers(String[] chasers) {
        if (chasers.length != 3) {
            throw new IllegalArgumentException("must have 3 chasers");
        }
        if (hasNull(chasers) || hasBlank(chasers)) {
            throw new IllegalArgumentException("Illegal elements");
        }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public static String getPositionChaser() {
        return POSITION_CHASER;
    }

    public static String getPositionSeeker() {
        return POSITION_SEEKER;
    }

    public static String getPositionKeeper() {
        return POSITION_KEEPER;
    }

    @Override
    public String toString() {
        return "House: " + this.house + "\n" +
                "Keeper: " + this.keeper + "\n" +
                "Seeker: " + this.seeker + "\n" +
                "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Team)) {
            return false;
        }
        Team team = (Team) obj;
        return house.equals(team.house) &&
                keeper.equals(team.keeper) &&
                seeker.equals(team.seeker) && Arrays.toString(chasers).equals(Arrays.toString(team.chasers));
    }

    public int hashCode() {
        return Objects.hash(house, keeper,seeker,Arrays.toString(chasers));
    }

}
