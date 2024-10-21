import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class MatchTest {

    @Test
    void testStartMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Team A", "Team B");
        List<String> matches = scoreboard.getSummaryFormatted();
        assertEquals(1, matches.size());
        assertEquals("Team A 0 - Team B 0", matches.get(0));
    }

    @Test
    void testUpdateScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Team A", "Team B");
        scoreboard.updateScore("Team A", "Team B", 1, 2);
        List<String> matches = scoreboard.getSummaryFormatted();
        assertEquals(1, matches.size());
        assertEquals("Team A 1 - Team B 2", matches.get(0));
    }

    @Test
    void testMatchImmutability() {
        Match match = new Match("Team A", "Team B", 0, 0, 1);

        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
        // Update score by creating a new match, verify immutability of the old match
        Match updatedMatch = new Match("Team A", "Team B", 1, 2, 1);
        assertEquals(0, match.getHomeScore()); // old match should still have 0-0 score
        assertEquals(0, match.getAwayScore());
        assertEquals(1, updatedMatch.getHomeScore()); // new match should have updated score
        assertEquals(2, updatedMatch.getAwayScore());
    }

    @Test
    void testNegativeScores() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");

        // Try to update the score with a negative home score, expect an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Mexico", "Canada", -1, 2));

        // Try to update the score with a negative away score, expect an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Mexico", "Canada", 1, -2));
    }
}
