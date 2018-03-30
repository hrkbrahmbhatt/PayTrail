package my.webs2canada.paytrail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import my.webs2canada.ctc.R;


/**
 * Created by hrsikeshbrahmbhatt on 2018-02-11.
 */

public class UserActivity extends AppCompatActivity{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        textView = findViewById(R.id.text1);

        String nameFromIntent = getIntent().getStringExtra("email");

        textView.setText("Welcome " + nameFromIntent);
    }

}
