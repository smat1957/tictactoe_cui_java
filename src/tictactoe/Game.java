package tictactoe;
import static tictactoe.Constants.*;

public class Game {

    private static Player[] player = null;
    private static Board currBoard = null;
    private static int current_player=0;

    public Game(boolean reverse) {
        player = new Player[2];
        if (reverse) {
            player[0] = new Player(MARU, "PC", MINIMAX);
            player[1] = new Player(BATSU, "You", PROMPT);
        } else {
            player[0] = new Player(BATSU, "You", PROMPT);
            player[1] = new Player(MARU, "PC", MINIMAX);
        }
        currBoard = new Board();
    }

    void Start() {
        current_player = 0;
        int winner = NEXT;
        System.out.println("スタート！ [Tic Tac Toe]");
        do {
            currBoard.print();
            Player currPlayer = player[current_player];
            currPlayer.putStone(currBoard);
            winner = currBoard.check();
            current_player = ++current_player % 2;
        } while (winner == NEXT);
        currBoard.print();
        result(winner);
    }

    private void result(int winner) {
        // 結果を表示する関数
        System.out.println("");
        switch (winner) {
            case DRAW:  System.out.print("引き分け\t");    break;
            case MARU:  System.out.print("'O' の勝ち\t");  break;
            case BATSU: System.out.print("'X' の勝ち\t");  break;
        }
        System.out.println("またね！");
    }

}
