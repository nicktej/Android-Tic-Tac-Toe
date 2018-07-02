package hu.ait.android.tictactoe.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import hu.ait.android.tictactoe.Data.TicTacToeModel;
import hu.ait.android.tictactoe.MainActivity;
import hu.ait.android.tictactoe.R;

/**
 * Created by nicktan on 9/14/17.
 */

public class TicTacToeView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintX;
    private Paint paintO;
    private Paint paintText;

    private Bitmap bitmapBg;

    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        paintO = new Paint();
        paintO.setColor(Color.YELLOW);
        paintO.setStyle(Paint.Style.STROKE);
        paintO.setStrokeWidth(10);

        paintX = new Paint();
        paintX.setColor(Color.RED);
        paintX.setStyle(Paint.Style.STROKE);
        paintX.setStrokeWidth(10);

        paintText = new Paint();
        paintText.setColor(Color.GREEN);
        paintText.setTextSize(60);

        bitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.background);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg, getWidth(), getHeight(), false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw background
        //canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
        canvas.drawBitmap(bitmapBg, 0, 0, null);

        // draw game field
        GameArea(canvas);

        // draw players (O, X)
        drawPlayers(canvas);
    }

    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, j)
                        == TicTacToeModel.CIRCLE) {
                    // draw circle
                    int centerX = i * getWidth() / 3 + getWidth() / 6;
                    int centerY = j * getHeight() / 3 + getHeight() / 6;

                    canvas.drawCircle(centerX, centerY, getWidth() / 6  - 2, paintO);

                } else if (TicTacToeModel.getInstance().getFieldContent(i, j)
                        == TicTacToeModel.CROSS){
                    // draw cross
                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, paintX);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3, paintX);

                }
            }
        }
    }

    private void GameArea(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        canvas.drawLine(0, getWidth()/3, getWidth(), getHeight()/3, paintLine);
        canvas.drawLine(0, 2*getWidth()/3, getWidth(), 2*getHeight()/3, paintLine);
        canvas.drawLine(getWidth()/3, 0, getWidth()/3, getHeight(), paintLine);
        canvas.drawLine(2*getWidth()/3, 0, 2*getWidth()/3, getHeight(), paintLine);
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {


            int tX = ((int)event.getX()) / (getWidth() / 3);
            int tY = ((int)event.getY()) / (getHeight() / 3);
            if (TicTacToeModel.getInstance().getFieldContent(tX, tY) == TicTacToeModel.WIN){

            }
            else if (TicTacToeModel.getInstance().getFieldContent(tX, tY) == TicTacToeModel.EMPTY) {
                TicTacToeModel.getInstance().setFieldContent(tX, tY,
                        TicTacToeModel.getInstance().getNextPlayer());

                TicTacToeModel.getInstance().switchNextPlayer();

                String nextPlayer = "O";
                if (TicTacToeModel.getInstance().getNextPlayer()
                        == TicTacToeModel.CROSS) {
                    nextPlayer = "X";
                }

                if (TicTacToeModel.getInstance().winPlayer() == 1){
                    String winner = "Circle wins!";
                    ((MainActivity)getContext()).setWinner(winner);

                    freezeBoard();
                };

                if (TicTacToeModel.getInstance().winPlayer() == 2){
                    String winner = "Cross wins!";
                    ((MainActivity)getContext()).setWinner(winner);

                    freezeBoard();
                };

                if (TicTacToeModel.getInstance().winPlayer() == 3){
                    String winner = "It's a draw!";
                    ((MainActivity)getContext()).setWinner(winner);

                    freezeBoard();
                };

                ((MainActivity)getContext()).setNextPlayer(nextPlayer);

                invalidate();
            }
        }

        return super.onTouchEvent(event);
    }

    public void freezeBoard() {

        TicTacToeModel.getInstance().stopGame();
        invalidate();
    }

    public void clearBoard() {

        TicTacToeModel.getInstance().resetGame();
        invalidate();
    }

}
