package hu.ait.android.tictactoe.Data;

/**
 * Created by nicktan on 9/18/17.
 */

public class TicTacToeModel {

    private static TicTacToeModel ticTacToeModel = null;

    private TicTacToeModel(){
    }

    public static TicTacToeModel getInstance(){
        if(ticTacToeModel == null){
            ticTacToeModel = new TicTacToeModel();
        }

        return ticTacToeModel;
    }

    public static final int EMPTY = 0;
    public static final int CIRCLE = 1;
    public static final int CROSS = 2;
    public static final int WIN = 3;
    private int [][] model = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private int nextPlayer = CIRCLE;

    public void switchNextPlayer() {

        /* code below same as
            if(nextPlayer == CIRCLE) {
                nextPlayer = CROSS;
            } else {
                nextPlayer = CIRCLE;
            }
        */
        nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;
    }

    public int winPlayer () {

        if (model[0][0] == 1 && model [0][1] == 1 && model [0][2] == 1 ||
                model[1][0] == 1 && model [1][1] == 1 && model [1][2] == 1 ||
                model[2][0] == 1 && model [2][1] == 1 && model [2][2] == 1 ||
                model[0][0] == 1 && model [1][0] == 1 && model [2][0] == 1 ||
                model[0][1] == 1 && model [1][1] == 1 && model [2][1] == 1 ||
                model[0][2] == 1 && model [1][2] == 1 && model [2][2] == 1 ||
                model[0][0] == 1 && model [1][1] == 1 && model [2][2] == 1 ||
                model[2][0] == 1 && model [1][1] == 1 && model [0][2] == 1 ) {

            return 1;
        }

        else if (model[0][0] == 2 && model [0][1] == 2 && model [0][2] == 2 ||
                model[1][0] == 2 && model [1][1] == 2 && model [1][2] == 2 ||
                model[2][0] == 2 && model [2][1] == 2 && model [2][2] == 2 ||
                model[0][0] == 2 && model [1][0] == 2 && model [2][0] == 2 ||
                model[0][1] == 2 && model [1][1] == 2 && model [2][1] == 2 ||
                model[0][2] == 2 && model [1][2] == 2 && model [2][2] == 2 ||
                model[0][0] == 2 && model [1][1] == 2 && model [2][2] == 2 ||
                model[2][0] == 2 && model [1][1] == 2 && model [0][2] == 2 ) {

            return 2;
        }

        else if (model[0][0] != 0 && model [0][1] != 0 && model [0][2] != 0 &&
                model[1][0] != 0 && model [1][1] != 0 && model [1][2] != 0 &&
                model[2][0] != 0 && model [2][1] != 0 && model [2][2] != 0 &&
                model[0][0] != 3 && model [1][0] != 3 && model [2][0] != 3 &&
                model[0][1] != 3 && model [1][1] != 3 && model [2][1] != 3 &&
                model[0][2] != 3 && model [1][2] != 3 && model [2][2] != 3 ) {

            return 3;
        }

        return 0;
    }

    public int getNextPlayer () {
        return nextPlayer;
    }

    public void setFieldContent(int x, int y, int content) {
        model[x][y] = content;
    }

    public int getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void stopGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (model[i][j] == EMPTY){
                    model[i][j] = WIN;
                }
            }
        }
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model[i][j] = EMPTY;
            }
        }
        nextPlayer = CIRCLE;

    }
}
