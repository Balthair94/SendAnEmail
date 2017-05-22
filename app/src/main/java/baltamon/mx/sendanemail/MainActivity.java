package baltamon.mx.sendanemail;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewHolder = new MainActivityViewHolder(findViewById(android.R.id.content));
        setUpToolbar();
        sendEmail();
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

    public void sendEmail(){
        viewHolder.getButtonSendEmail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SendMailActivity", "Send Button Clicked.");

                String fromEmail = viewHolder.getEditTextFrom().getText().toString();
                String fromPassword = viewHolder.getEditTextPassword().getText().toString();
                String toEmails = viewHolder.getEditTextTo().getText().toString();
                List toEmailList = Arrays.asList(toEmails
                        .split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmailList);
                String emailSubject = viewHolder.getEditTextSubject().getText().toString();
                String emailBody = viewHolder.getEditTextBody().getText().toString();
                new SendMailTask(MainActivity.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);
            }
        });
    }
}
