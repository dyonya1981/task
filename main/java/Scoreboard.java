/**
 * Manages the scoreboard for ongoing football matches.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Scoreboard implements IScoreboard{
    private final List<Match> matches;
    private int matchCounter = 0; // Counter to track match creation order

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }
    /**
     * Starts a new match with the given teams.
     *
     * @throws 'IllegalArgumentException' if the input is invalid.
     */
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
        // Create a new match with the current counter as the creation index
        Match match = new Match(homeTeam, awayTeam, matchCounter++);
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

        // Preserve the original creation index
        int creationIndex = existingMatch.getCreationIndex();

        // Remove the old match and add a new match with updated scores
        matches.remove(existingMatch);
        Match updatedMatch = new Match(homeTeam, awayTeam, homeScore, awayScore, creationIndex);
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

        for (Match match : matches) {
            summary.add(formatMatchForOutput(match));
        }

        return summary;
    }

    private String formatMatchForOutput(IMatch match) {
        return match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayTeam() + " " + match.getAwayScore();
    }

    // Return matches ordered by total score, using creation index for tie-breaking
    public List<IMatch> getSummaryOrderedByScore() {
        matches.sort(Comparator
                .comparingInt((Match m) -> m.getHomeScore() + m.getAwayScore()) // Sort by total score higher scores first
                .thenComparingInt(Match::getCreationIndex).reversed()); // For ties, higher creation index first (i.e., more recent)

        return new ArrayList<>(matches);
    }
}
