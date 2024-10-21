/**
 * Represents a football match with home and away teams.
 * This class is immutable, meaning once created, its state cannot change.
 */

public class Match implements IMatch {

    private final String homeTeam;
    private final String awayTeam;

    private final int creationIndex; // Track the creation index
    private int homeScore;
    private int awayScore;
    /**
     * Constructs a Match instance with specified teams and scores.
     *
     * @param homeTeam    Name of the home team.
     * @param awayTeam    Name of the away team.
     * @param creationIndex Index indicating the match's creation order.
     */
    public Match(String homeTeam, String awayTeam, int creationIndex) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.creationIndex = creationIndex;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    // Constructor for creating a new Match instance with updated scores
    public Match(String homeTeam, String awayTeam, int homeScore, int awayScore, int creationIndex) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.creationIndex = creationIndex;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() { return homeScore; }

    public int getAwayScore() {
        return awayScore;
    }

    public int getCreationIndex() { return creationIndex;}
}

