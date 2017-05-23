package baltamon.mx.sendanemail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private static final int LOAD_IMAGE = 02;

    private MainActivityViewHolder viewHolder;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewHolder = new MainActivityViewHolder(findViewById(android.R.id.content));
        setUpToolbar();
        sendEmail();

        if (isOnline())
            sendEmail();

        loadLocalImage();
    }

    public void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Send an email");
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public void sendEmail(){
        viewHolder.getButtonSendEmail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Send email", "");
                String[] TO = {"galuz01@gmail.com"};

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                if (imageUri != null){
                    intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    intent.setType("image/*");
                }
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.putExtra(Intent.EXTRA_SUBJECT, viewHolder.getEditTextSubject().getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, viewHolder.getEditTextBody().getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    showToast("No email provider detected");
            }
        });
    }

    public void loadLocalImage(){
        viewHolder.getButtonLoadLocalImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case LOAD_IMAGE:
                if (resultCode == Activity.RESULT_OK){
                    imageUri = data.getData();
                    try {
                        Bitmap myBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        viewHolder.getImageView().setImageBitmap(myBitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
