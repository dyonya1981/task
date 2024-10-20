import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Scoreboard {
    private final List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = getMatch(homeTeam, awayTeam);
        if (match != null) {
            match.updateScore(homeScore, awayScore);
        }
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

    public List<Match> getSummary() {
        matches.sort(Comparator.comparingInt((Match m) -> m.getHomeScore() + m.getAwayScore())
                .reversed().thenComparing(matches::indexOf));
        return new ArrayList<>(matches);
    }
}
