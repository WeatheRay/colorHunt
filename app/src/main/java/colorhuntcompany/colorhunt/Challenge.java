package colorhuntcompany.colorhunt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap;

import static android.graphics.Color.rgb;

public class Challenge extends AppCompatActivity {
    public static Activity chal;
    static int loaded=0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void cameraButton(View v){
        dispatchTakePictureIntent(1);

    }
    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);

    }

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        Bitmap mImageBitmap = (Bitmap) extras.get("data");
        //ImageView mImageView=findViewById(R.id.mImageView);
        //mImageView.setImageBitmap(mImageBitmap);
        compress(mImageBitmap, "image");
    }
    public void compress( Bitmap bitmap,String data)
    {
        try {
            FileOutputStream fout = new FileOutputStream(getFilesDir().toString()+data+".jpeg");
            //String test = getFilesDir().toString();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
        }
        catch(IOException e)
        {
            Log.i("E",e.getMessage());

        }
    }

    //called after camera intent finished taking photo
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent   intent) {
        loaded=1;
        handleSmallCameraPhoto(intent);
        Bitmap mImageBitmap = BitmapFactory.decodeFile(getFilesDir().toString()+"image.jpeg");
        ImageView mImageView=findViewById(R.id.mImageView);
        mImageView.setImageBitmap(mImageBitmap);
        loaded=0;
        startActivity(new Intent(Challenge.this, Results.class));
    }



                /*
                Uri photoURI = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".provider",
                        photoFile);
                        */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.fa.finish();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        chal = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        // /data/user/0/colorhuntcompany.colorhunt/files
        //String test = getFilesDir().toString();
        Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        int max = 255;
        int min = 0;
        // create instance of Random class
        if(loaded==0) {
            Random randomNum = new Random();
            image.eraseColor(rgb(randomNum.nextInt(max - min), randomNum.nextInt(max - min), randomNum.nextInt(max - min)));
            ImageView mImageView = findViewById(R.id.mImageView);
            mImageView.setImageBitmap(image);
            compress(image, "target");
            loaded = 1;
        }
        else {
            ImageView v = findViewById(R.id.mImageView);
            v.setImageBitmap(BitmapFactory.decodeFile(getFilesDir() + "target.jpeg"));
        }

    }

}
