package colorhuntcompany.colorhunt;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;


public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //TODO: create and set global variable targetcolor
        String picturePath = getFilesDir().toString() + "image.jpeg";

        int target = BitmapFactory.decodeFile(getFilesDir()+"target.jpeg").getPixel(0,0);

        ProcessResult(target, ColorSearch(BitmapFactory.decodeFile(picturePath)));

    }

    public ArrayList<Integer> ColorSearch(Bitmap orig)
    {
        Bitmap img = Bitmap.createScaledBitmap(orig,5,5,true);
        ArrayList<Integer> colors = new ArrayList();
        for (int i = 0; i<5; i++)
        {
            for (int j = 0; j<5; j++)
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
            int score = (Math.abs(targetR-R)+Math.abs(targetB-B)+Math.abs(target));
            scores.add((765-score)*1000/765);
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
        //TODO: remove the hard coding here
        final int bestf = 150;
        //TODO: Write a pass fail condition for a color being too far away or not
        TextView test = findViewById(R.id.textView8);
        test.setVisibility(View.INVISIBLE);

        CountDownTimer t = new CountDownTimer(100*best/5, 100) {
            public int counted = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                ProgressBar p = findViewById(R.id.progressBar);
                if(bestf-counted >=5)
                {

                    p.setProgress(p.getProgress()+5);
                    counted+=5;
                }
                else {
                    p.setProgress(p.getProgress() + bestf - counted);
                    counted = bestf;
                }
                TextView t = findViewById(R.id.textView7);
                t.setText(counted+"/1000");
            }

            @Override
            public void onFinish() {

            }
        }.start();




    }
}
