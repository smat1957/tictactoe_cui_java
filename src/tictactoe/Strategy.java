package tictactoe;
import static tictactoe.Constants.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Strategy {
    int prompt(String name) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = -1;
        String fmt = null, instr = null;
        do {
            fmt = String.format("\n石を置く場所を番号で指定（ %s の番です ）：", name);
            System.out.print(fmt);
            try {
                instr = br.readLine();
                num = Integer.parseInt(instr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!(0 <= num && num < NXN * NXN)) {
                System.out.println("0～" + (NXN * NXN - 1) + " の番号を指定して");
                continue;
            }
            break;
        } while (true);
        return num;
    }
    int random(String name) {
        System.out.printf("。。。　%s 思考中　。。。", name);
        return (int) (Math.random() * (NXN * NXN));
    }

    private float minimax(Board board, int depth, int turn, Player player) {
        float best, score;
        if (board.isWin() || board.isDraw() || depth == 0) {
            score = board.evaluate(player, turn);
            return score;
        }
        if (turn == MAX) {
            best = (float) Integer.MIN_VALUE;
        } else {
            best = (float) Integer.MAX_VALUE;
        }
        for (int i = 0; i < NXN * NXN; i++) {
            Stone s = new Stone(i, turn);
            if (board.canPut(s)) {
                board.setBoard(s);
                score = minimax(board, depth - 1, -turn, player);
                board.setEmpty(i);
                if (turn == MAX)
                    if (score > best) best = score;
                else
                    if (score < best) best = score;
            }
        }
        return best;
    }
    int bestMoveMM(Board board, Player player) {
        float bestEval = (float) Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < NXN * NXN; i++) {
            Stone s = new Stone(i, player.getColor());
            if (board.canPut(s)) {
                board.setBoard(s);  //BLACKに打たせるには-player.getColor()の負号をとり
                float eval = minimax(board, 8, -player.getColor(), player);
                board.setEmpty(i);  //Board.evaluate で -1 と 1 を入れ替える
                if (eval > bestEval) {
                    bestEval = eval;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

}
