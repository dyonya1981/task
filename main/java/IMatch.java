public interface IMatch {
    String getHomeTeam();
    String getAwayTeam();
    int getHomeScore();
    int getAwayScore();
    void updateScore(int homeScore, int awayScore);
}