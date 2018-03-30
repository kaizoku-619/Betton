package net.kaizoku.betton;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.kaizoku.betton.model.Player;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "Betton";
    private Button button;
    private TextView time;
    private TextView score;
    private TextView level;
    private int x, y, r, pScore, pLevel;
    private Random random;
    private Player player;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initPlayer();
        initView();
        initListeners();
        countDownTimer();
        buttonPositionTimer();
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        button.setTranslationX(420);
        button.setTranslationY(720);
        button.setRotation(30);

        time = (TextView) findViewById(R.id.time);

        score = (TextView) findViewById(R.id.score);
        level = (TextView) findViewById(R.id.level);
    }

    private void initListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    randomPosition();
                    incrementScore();
                    incrementLevel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void randomPosition() {
        x = random.nextInt((840-10)+1)+10;
        y = random.nextInt((1450-200)+1)+200;
        r = random.nextInt((360-0)+1)+0;
        button.setTranslationX(x);
        button.setTranslationY(y);
        button.setRotation(r);
        Log.i(TAG, "x = " + x + " y = " + y + " r = " + r);
    }

    private void initPlayer() {
        random = new Random();
        player = new Player();
        pLevel = player.getLevel();
        pScore = player.getScore();
        countDownTimer();
    }

    private void incrementScore() {
        pScore++;
        player.setScore(pScore);
        score.setText(pScore+"");
    }

    private void incrementLevel() {
        if (pScore % 10 == 0) {
            pLevel++;
            player.setScore(pLevel);
            level.setText(pLevel+"");
        }
    }

    private void countDownTimer() {
        countDownTimer = new CountDownTimer(60 * 1000, 1 * 1000) {
            public void onTick(long millisUntilFinished) {
                time.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                time.setText("done!");
            }
        }.start();
    }

    private void buttonPositionTimer() {
        countDownTimer = new CountDownTimer(60 * 1000, 900) {
            public void onTick(long millisUntilFinished) {
                randomPosition();
            }

            public void onFinish() {
                time.setText("done!");
            }
        }.start();
    }
}
