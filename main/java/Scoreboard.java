import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Scoreboard implements IScoreboard{
    private final List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        if (getMatch(homeTeam, awayTeam) != null) {
            throw new IllegalArgumentException("Match already exists");
        }
        if (homeTeam == null || homeTeam.trim().isEmpty() || awayTeam == null || awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Team names cannot be null or empty");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Teams cannot be the same");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        Match existingMatch = getMatch(homeTeam, awayTeam);
        if (existingMatch == null) {
            throw new IllegalArgumentException("Match does not exist");
        }
        // Remove the old match and add a new match with updated scores
        matches.remove(existingMatch);
        Match updatedMatch = new Match(homeTeam, awayTeam, homeScore, awayScore);
        matches.add(updatedMatch);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = getMatch(homeTeam, awayTeam);
        if (match != null) {
            matches.remove(match);
        }
    }

    public Match getMatch(String homeTeam, String awayTeam) {
        Optional<Match> match = matches.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
                .findFirst();
        return match.orElse(null);
    }

    public List<String> getSummaryFormatted() {
        List<String> summary = new ArrayList<>();

        // Iterate over the internal matches directly
        for (Match match : matches) { // Assuming matches is a private List<Match>
            summary.add(formatMatchForOutput(match));
        }

        return summary;
    }

    private String formatMatchForOutput(IMatch match) {
        return match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayTeam() + " " + match.getAwayScore();
    }
}
