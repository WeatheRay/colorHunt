package colorhuntcompany.colorhunt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Achievements extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        String test = getFilesDir().toString()+"/stats";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(test));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String past="";
        try {
            past = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parts = past.split(",");
        int totalScore=Integer.parseInt(parts[0]);
        int bestScore=Integer.parseInt(parts[1]);
        int gamesPlayed=Integer.parseInt(parts[2]);
        int perfectGames = Integer.parseInt(parts[3]);
        TextView ta= findViewById(R.id.challenges_textview);
        ta.setText(gamesPlayed+"");
        ta = findViewById(R.id.points_testview);
        ta.setText(totalScore+"");
        ta= findViewById(R.id.topscore_textview);
        ta.setText(bestScore+"");
        ta=findViewById(R.id.perfects_testview);
        ta.setText(perfectGames+"");
        ProgressBar p = findViewById(R.id.progressBar2);
        p.setProgress(bestScore);
        p.setMax(1000);
   }

    public void clickStats(View name)
    {
        startActivity(new Intent(Achievements.this, MainActivity.class));
    }
}
