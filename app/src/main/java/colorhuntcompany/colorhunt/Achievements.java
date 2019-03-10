package colorhuntcompany.colorhunt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Achievements extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
    }

    public void clickStats(View name)
    {
        startActivity(new Intent(Achievements.this, MainActivity.class));
    }
}
