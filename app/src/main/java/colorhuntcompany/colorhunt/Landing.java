package colorhuntcompany.colorhunt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ImageView simpleImageView=(ImageView) findViewById(R.id.logo);
        simpleImageView.setImageResource(R.drawable.logo);
        Log.i("e",getFilesDir().toString());
        //following is test code
        //Resources res = getResources();

        //Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.blacktest);

        //compress(bitmap)
//        int target = bitmap.getPixel(1,1);
//        int targetR =  red(target);
//        int targetG = green(target);
//        int targetB = blue(target);
//        Log.i("Black values",targetR + ", " + targetG+ ", "+ targetB);
//        bitmap = BitmapFactory.decodeResource(res,R.drawable.whitetest);
//
//        target=bitmap.getPixel(1,1);
//        targetR =  red(target);
//        targetG = green(target);
//        targetB = blue(target);
//        Log.i("White values",targetR + ", " + targetG+ ", "+ targetB);
    }
    public void clickStart(View name)
    {
        startActivity(new Intent(Landing.this, MainActivity.class));
    }
    public void compress( Bitmap bitmap)
    {
        try {
            FileOutputStream fout = new FileOutputStream(getFilesDir().toString()+"image.jpeg");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
        }
        catch(IOException e)
        {
            Log.i("E",e.getMessage());

        }
    }
}
