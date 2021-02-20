package tictactoe;
import static tictactoe.Constants.*;

public class Player extends Strategy {

    private String Name = null;
    private int Senryaku = 0;
    private int Color = EMPTY;

    Player(int i, String n, int s) {
        Color = i;
        Name = n;
        Senryaku = s;
    }

    void putStone(Board board) {
        Stone s=null;
        do {
            int te = Te(board);
            s = new Stone(te, Color);
            if ( board.canPut(s) ) {
                break;
            }
        } while (true);
        board.setBoard( s );
    }

    private int Te(Board board) {
        int v = 0;
        switch (Senryaku) {
            case PROMPT:  v = prompt(Name); break;
            case RANDOM:  v = random(Name); break;
            case MINIMAX: v = bestMoveMM(board, this); break;
            case ALPHABETA:   break;
            case MONTECARLO:  break;
        }
        return v;
    }

    int getColor(){
        return Color;
    }
}
