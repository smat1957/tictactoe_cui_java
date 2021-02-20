package tictactoe;
import static tictactoe.Constants.*;

public class Board {
    private Stone[] stones = new Stone[NXN*NXN];;
    public Board() {
        for (int i = 0; i < NXN * NXN; i++) {
            stones[i] = new Stone(i, EMPTY);
        }
    }
    void setEmpty(int i){
        stones[i]=new Stone(i, EMPTY);
    }
    void setBoard(Stone s) {
        stones[s.getLocate()] = s;
    }
    Stone getBoard(int i){
        return stones[i];
    }
    boolean canPut(Stone s) {
        int n=s.getLocate();
        if( !((0<=n)&&(n<NXN*NXN)) )  return false;
        if (stones[s.getLocate()].getColor() != EMPTY)  return false;
        return true;
    }
    public void print() {
        //盤面を表示する関数
        char[] bd = new char[NXN*NXN];
        for (int i = 0; i < NXN*NXN; i++) {
            if (stones[i].getColor() == MARU) {
                bd[i] = 'O';
            } else if (stones[i].getColor() == BATSU) {
                bd[i] = 'X';
            } else {
                bd[i] = (char) ('0' + i);
            }
        }
        String fmt;
        System.out.println("\n/---|---|---\\");
        fmt = String.format("| %c | %c | %c |", bd[0], bd[1], bd[2]);
        System.out.println(fmt);
        System.out.println("|---|---|---|");
        fmt = String.format("| %c | %c | %c |", bd[3], bd[4], bd[5]);
        System.out.println(fmt);
        System.out.println("|---|---|---|");
        fmt = String.format("| %c | %c | %c |", bd[6], bd[7], bd[8]);
        System.out.println(fmt);
        System.out.println("\\---|---|---/");
    }
    private int lineSum(int n1, int n2, int n3) {
        return stones[n1].getColor() + stones[n2].getColor() + stones[n3].getColor();
    }

    public int check() {
        //勝敗を判定する関数
        int i, l = 0;
        for (i = 0; i < (NXN+NXN+2); i++) {
            switch (i) {
                case 0: l = lineSum(0, 1, 2); break;
                case 1: l = lineSum(3, 4, 5); break;
                case 2: l = lineSum(6, 7, 8); break;
                case 3: l = lineSum(0, 3, 6); break;
                case 4: l = lineSum(1, 4, 7); break;
                case 5: l = lineSum(2, 5, 8); break;
                case 6: l = lineSum(0, 4, 8); break;
                case 7: l = lineSum(2, 4, 6); break;
            }
            if (l == NXN * MARU) {
                return MARU;
            } else if (l == NXN * BATSU) {
                return BATSU;
            }
        }
        for (i = 0; i < NXN*NXN; i++) {
            if (stones[i].getColor() == EMPTY) {
                return NEXT;
            }
        }
        return DRAW;
    }

    private boolean line(int n1, int n2, int n3){
        return (( stones[n1].getColor()!=EMPTY ) &&
                ( stones[n1].getColor()==stones[n2].getColor() ) &&
                ( stones[n1].getColor()==stones[n3].getColor() ));
    }

    boolean isWin(){
        // もう一つの勝敗を判定する関数
        return (line(0,1,2)||
                line(3,4,5)||
                line(6,7,8)||
                line(0,3,6)||
                line(1,4,7)||
                line(2,5,8)||
                line(0,4,8)||
                line(2,4,6));
    }
    boolean isDraw(){
        // 勝敗がついていないのに、EMPTYのスロットがもうないならDRAW　
        int nEmpty=0;
        for(int i=0; i<NXN*NXN; i++){
            if(stones[i].getColor()==EMPTY)nEmpty++;
        }
        if(!isWin() && nEmpty==0)return true;
        return false;
    }

    float evaluate(Player p, int turn){ // 局面を評価する（MINIMAX法が使う）
        if(isWin()){
            if(turn==p.getColor())return (float)(-1.0);
            else if(turn!=p.getColor())return (float)(1.0);
        }
        return (float)0.0;
    }
}
