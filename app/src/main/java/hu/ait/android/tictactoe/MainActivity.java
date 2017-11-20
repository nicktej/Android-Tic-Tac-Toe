package hu.ait.android.tictactoe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import hu.ait.android.tictactoe.View.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private LinearLayout layoutContent;
    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        winner = (TextView) findViewById(R.id.winner);
        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        final TicTacToeView ticTacToeView = (TicTacToeView) findViewById(R.id.ticTacToeView);
        Button btnClear = (Button) findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ticTacToeView.clearBoard();
                setNextPlayer("O");
                winner.setText("");
            }


        });

        ShimmerFrameLayout shimmerView = findViewById(R.id.shimmer_view);
        shimmerView.startShimmerAnimation();

    }

    public void setWinner(String WIN) {
        Log.d("TAG_Main", WIN);
        String txt = getString(R.string.winner, WIN);

        winner.setText(txt);
    }

    public void setNextPlayer(String nextPlayer) {
        String text = getString(R.string.text_next_player, nextPlayer);

        tvStatus.setText(text);
        Snackbar.make(layoutContent, text, Snackbar.LENGTH_LONG).show();

        Snackbar.make(layoutContent, "Next turn", Snackbar.LENGTH_LONG).setAction(
                "Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                }
        ).show();


    }

}
