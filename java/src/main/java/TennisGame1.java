import lombok.Getter;

public class TennisGame1 implements TennisGame {
    private final Player player1;
    private final Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (player1.getName().equals(playerName))
            player1.wonPoint();
        else
            player2.wonPoint();
    }

    public String getScore() {
        return isDraw() ? drawScore(player1) // or player2, since they are equal
                : isAdv() ? advScore(getWinningPlayer())
                : isWin() ? winScore(getWinningPlayer())
                : normalScore(player1, player2);

    }

    private String winScore(Player winningPlayer) {
        return "Win for " + winningPlayer.getName();
    }

    private String advScore(Player winningPlayer) {
        return "Advantage " + winningPlayer.getName();
    }

    private String drawScore(Player anyPlayer) {
        return switch (anyPlayer.getScore()) {
            case 0, 1, 2 -> anyPlayer.scoreString() + "-All";
            default -> "Deuce";
        };
    }

    private String normalScore(Player p1, Player p2) {
        return p1.scoreString() + "-" + p2.scoreString();
    }

    private boolean isDraw() {
        return player1.compareTo(player2) == 0;
    }

    private boolean isAdv() {
        return (player1.getScore() >= 4 || player2.getScore() >= 4)
                && Math.abs(player1.getScore() - player2.getScore()) == 1;
    }

    private boolean isWin() {
        return (player1.getScore() >= 4 || player2.getScore() >= 4)
                && Math.abs(player1.getScore() - player2.getScore()) >= 2;
    }

    private Player getWinningPlayer() {
        return player1.compareTo(player2) > 0 ? player1 : player2;
    }

    @Getter
    public static class Player implements Comparable<Player> {
        private final String name;
        private int score;

        public Player(String playerName) {
            this.name = playerName;
            this.score = 0;
        }

        public void wonPoint() {
            score++;
        }

        public String scoreString() {
            return switch (getScore()) {
                case 0 -> "Love";
                case 1 -> "Fifteen";
                case 2 -> "Thirty";
                case 3 -> "Forty";
                default -> "";
            };
        }

        @Override
        public int compareTo(Player o) {
            return Integer.compare(score, o.score);
        }
    }

}
