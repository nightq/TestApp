package com.example.nightq.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static int dp2pX(int x) {
        return 3 * x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameOverTransitAnimView gameOverTransitAnimView = (GameOverTransitAnimView)
                findViewById(R.id.gameOverTransitAnimView);
        gameOverTransitAnimView.show();

        gameOverTransitAnimView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOverTransitAnimView.show();
            }
        });
    }

}
