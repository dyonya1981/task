public class Match implements IMatch {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isEmpty() || awayTeam == null || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Team names cannot be null or empty");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Teams cannot be the same");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}

