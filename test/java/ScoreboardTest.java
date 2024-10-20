import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setup() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testStartMatch() {
        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(1, scoreboard.getSummaryFormatted().size());
    }

    @Test
    void testUpdateScore() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 1, 2);
        Match match = scoreboard.getMatch("Mexico", "Canada");
        assertEquals(1, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void testFinishMatch() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.finishMatch("Mexico", "Canada");
        assertEquals(0, scoreboard.getSummaryFormatted().size());
    }

    @Test
    void testGetSummaryOrderByTotalScore() {

        // Start matches
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");

        // Update scores
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Mexico", "Canada", 0, 5);

        // Get formatted summary
        List<String> summary = scoreboard.getSummaryFormatted();

        // Verify that matches are in the correct order based on total score and recency
        assertEquals("Spain 10 - Brazil 2", summary.get(0));
        assertEquals("Mexico 0 - Canada 5", summary.get(1));
    }

    @Test
    void testDuplicateMatch() {
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", "Canada"));
    }

    @Test
    void testInvalidScoreUpdate() {
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Mexico", "Canada", -1, 2));
    }

    @Test
    void testFinishNonExistentMatch() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.finishMatch("Spain", "Brazil");
        assertEquals(1, scoreboard.getSummaryFormatted().size());  // Match still exists
    }

    @Test
    void testUpdateScoreForNonExistingMatch() {
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Spain", "Brazil", 3, 1));
    }

    @Test
    void testGetSummaryOutput() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 3, 1);
        List<String> summary = scoreboard.getSummaryFormatted();
        assertEquals("Mexico 3 - Canada 1", summary.get(0));
    }

    @Test
    void testInvalidTeamNames() {
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", "Canada"));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", ""));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, "Canada"));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", null));
    }
}
