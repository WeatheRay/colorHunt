package colorhuntcompany.colorhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ImageView simpleImageView=(ImageView) findViewById(R.id.logo);
        simpleImageView.setImageResource(R.drawable.logo);
    }
    public void clickStart(View name)
    {
        startActivity(new Intent(Landing.this, MainActivity.class));
    }
}
