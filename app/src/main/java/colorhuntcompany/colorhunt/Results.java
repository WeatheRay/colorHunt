package colorhuntcompany.colorhunt;

import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;


public class Results extends AppCompatActivity {
    static int challengeNumber =1;
    static int challengeScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Challenge.chal.finish();

        challengeNumber = challengeNumber%6;
        if(challengeNumber==0) {
            challengeNumber++;
            challengeScore=0;
        }
        Log.i("challenge num"," "+challengeNumber);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //TODO: create and set global variable targetcolor
        String picturePath = getFilesDir().toString() + "image.jpeg";
        ImageView v = findViewById(R.id.target_imageview);
        v.setImageBitmap(BitmapFactory.decodeFile(getFilesDir()+"target.jpeg"));
        int target = BitmapFactory.decodeFile(getFilesDir()+"target.jpeg").getPixel(0,0);
        v =  findViewById(R.id.closest_imageview);
        v.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        ProcessResult(target, ColorSearch(BitmapFactory.decodeFile(picturePath)));

    }
    public void statsButton(View v){
        startActivity(new Intent(Results.this, Achievements.class));
    }

    public ArrayList<Integer> ColorSearch(Bitmap orig)
    {
        Bitmap img = Bitmap.createScaledBitmap(orig,10,10,true);
        ArrayList<Integer> colors = new ArrayList();
        for (int i = 0; i<10; i++)
        {
            for (int j = 0; j<10; j++)
            {
                colors.add(img.getPixel(i,j));
            }
        }
        return colors;
    }

    protected void ProcessResult(int target, ArrayList<Integer> colors)
    {
        ArrayList<Integer> scores = new ArrayList();
        int targetR =  red(target);
        int targetG = green(target);
        int targetB = blue(target);
        for (Integer color:colors)
        {


            int R=red(color);
            int G = green(color);
            int B = blue(color);
            int score = (Math.abs(targetR-R)+Math.abs(targetB-B)+Math.abs(targetG-G));
            scores.add(((Math.max((((765-score)*1000/765)-500),0)*2)));
        }
        Log.i("SCORES", scores.toString());
        int best = 0;
        int bestIndex = -1;
        for (int i=0; i<scores.size();i++)
        {
            if(scores.get(i)>=best)
            {
                best = scores.get(i);
                bestIndex = i;
            }
        }
        ImageView v2 =findViewById(R.id.closest_imageview);
        Bitmap b2 = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
        b2.eraseColor(colors.get(bestIndex));
        v2.setImageBitmap(b2);
        //TODO: remove the hard coding here
        final int bestf = best;
        //TODO: Write a pass fail condition for a color being too far away or not
        final int SPEED = 100;
        final int INCREMENT = 20;
        CountDownTimer t = new CountDownTimer(SPEED*best/INCREMENT, SPEED) {
            public int counted = 0;
            @Override
            public void onTick(long millisUntilFinished) {

                ProgressBar p = findViewById(R.id.progressBar);
                TextView t = findViewById(R.id.textView7);
                if(bestf-counted >=INCREMENT)
                {

                    p.setProgress(p.getProgress()+INCREMENT);
                    counted+=INCREMENT;
                    t.setText(counted+"/1000");
                }


            }

            @Override
            public void onFinish() {
                ProgressBar p = findViewById(R.id.progressBar);
                    p.setProgress(bestf);
                    counted = bestf;

                TextView t = findViewById(R.id.textView7);
                t.setText(counted+"/1000");
                new CountDownTimer(100, 100) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        findViewById(R.id.closest_imageview).setVisibility(View.VISIBLE);
                        findViewById(R.id.textView9).setVisibility(View.VISIBLE);
                        findViewById(R.id.textView10).setVisibility(View.VISIBLE);
                        TextView ta= findViewById(R.id.challenge_progress_textview);
                        ta.setVisibility(View.VISIBLE);
                        ta.setText(challengeNumber+"/5");
                        challengeScore+= bestf;
                        ta = findViewById(R.id.textView13);
                        ta.setVisibility(View.VISIBLE);
                        ta.setText(""+challengeScore);
                        findViewById(R.id.textView12);
                        if (challengeNumber!=5)
                        {
                            findViewById(R.id.next_color_button).setVisibility(View.VISIBLE);

                        }
                        else
                        {
                            findViewById(R.id.stats_button).setVisibility(View.VISIBLE);
                            findViewById(R.id.play_again_button).setVisibility(View.VISIBLE);
                            findViewById(R.id.open_home_button).setVisibility(View.VISIBLE);

                        }




                        challengeNumber ++;
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.

                    }
                }.start();





            }
        }.start();




    }
    public void clickNext(View name)
    {
        startActivity(new Intent(Results.this, Challenge.class));
    }
    public void clickHome(View name)
    {
        startActivity(new Intent(Results.this, MainActivity.class));
    }
    public void clickStats(View name)
    {
        startActivity(new Intent(Results.this, Achievements.class));
    }
    public void clickPlay(View name)
    {
        startActivity(new Intent(Results.this, Challenge.class));
    }

}
