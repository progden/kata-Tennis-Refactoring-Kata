
public class TennisGame1 implements TennisGame {
    private final Player p1;
    private final Player p2;

    public TennisGame1(String player1Name, String player2Name) {
        p1 = new Player(player1Name);
        p2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (playerName == p1.name)
            p1.wonPoint();
        else
            p2.wonPoint();
    }

    public String getScore() {
        return isDraw() ? drawScore()
                : isAdv() ? advScore(getWinningPlayer())
                : isWin() ? winScore(getWinningPlayer())
                : normalScore();
    }

    // 顯示分數結果相關的 API
    private String winScore(Player winningPlayer) {
        return "Win for " + winningPlayer.name;
    }

    private String advScore(Player winningPlayer) {
        return "Advantage " + winningPlayer.name;
    }

    private String drawScore() {
        // p1 or p2 is fine, since they are the same
        return switch (p1.score) {
            case 0, 1, 2 -> p1.scoreString() + "-All";
            default -> "Deuce";
        };
    }

    private String normalScore() {
        return p1.scoreString() + "-" + p2.scoreString();
    }

    // 判斷遊戲結果相關的 API
    private boolean isWin() {
        return isAdvOrWin() && scoreGap() >= 2;
    }

    private boolean isAdv() {
        return isAdvOrWin() && scoreGap() == 1;
    }

    private boolean isDraw() {
        return p1.score == p2.score;
    }

    private boolean isAdvOrWin() {
        return p1.score >= 4 || p2.score >= 4;
    }

    private int scoreGap() {
        return Math.abs(p1.score - p2.score);
    }

    private Player getWinningPlayer() {
        return p1.score - p2.score > 0 ? p1 : p2;
    }

    public static class Player {
        private final String name;
        private int score = 0;

        public Player(String playerName) {
            this.name = playerName;
        }

        private void wonPoint() {
            score = score + 1;
        }

        private String scoreString() {
            return switch (score) {
                case 0 -> "Love";
                case 1 -> "Fifteen";
                case 2 -> "Thirty";
                case 3 -> "Forty";
                default -> throw new IllegalStateException("Unexpected value: " + score);
            };
        }
    }
}
