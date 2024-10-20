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
        assertEquals(1, scoreboard.getSummary().size());
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
        assertEquals(0, scoreboard.getSummary().size());
    }

    @Test
    void testGetSummaryOrderByTotalScore() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);

        List<IMatch> summary = scoreboard.getSummary();
        assertEquals("Spain 10 - Brazil 2", summary.get(0).toString());
        assertEquals("Mexico 0 - Canada 5", summary.get(1).toString());
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
        assertEquals(1, scoreboard.getSummary().size());  // Match still exists
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
}
