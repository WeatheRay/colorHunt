package colorhuntcompany.colorhunt;



import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public static Activity fa;
    public void challengeButton(View v){
            startActivity(new Intent(MainActivity.this, Challenge.class));
        }
    public void statsButton(View v){
        startActivity(new Intent(MainActivity.this, Achievements.class));
    }




                /*
                Uri photoURI = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".provider",
                        photoFile);
                        */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fa = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dispatchTakePictureIntent(1);
        // /data/user/0/colorhuntcompany.colorhunt/files
        String test = getFilesDir().toString();

    }


}
