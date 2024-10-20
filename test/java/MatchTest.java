
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchInitialization() {
        Match match = new Match("Mexico", "Canada");
        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void testUpdateScore() {
        Match match = new Match("Mexico", "Canada");
        match.updateScore(1, 2);
        assertEquals(1, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void testInvalidTeamNames() {
        assertThrows(IllegalArgumentException.class, () -> new Match("", "Canada"));
        assertThrows(IllegalArgumentException.class, () -> new Match("Mexico", ""));
        assertThrows(IllegalArgumentException.class, () -> new Match(null, "Canada"));
    }

    @Test
    void testNegativeScores() {
        Match match = new Match("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 2));
    }
}
