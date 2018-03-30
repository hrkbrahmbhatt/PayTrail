package my.webs2canada.paytrail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import my.webs2canada.ctc.R;
import my.webs2canada.paytrail.helper.InputValidation;
import my.webs2canada.paytrail.model.User;
import my.webs2canada.paytrail.sql.DatabaseHelper;


/**
 * Created by hrsikeshbrahmbhatt on 2018-02-11.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;


    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;


    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView textViewLinkLogin;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    Thread waitThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){

        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputEditTextName = findViewById(R.id.textInputEditTextName);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);

        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfrimPassword);
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditTextConfirmPassword);


        appCompatButtonRegister =  findViewById(R.id.appCompatButtonRegister);

        textViewLinkLogin = findViewById(R.id.textViewLinkLoginLink);
    }

    private void initListeners(){

        appCompatButtonRegister.setOnClickListener(this);
        textViewLinkLogin.setOnClickListener(this);
    }
    private void initObjects(){

        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLLite();
                break;
            case R.id.textViewLinkLoginLink:
              finish();
                break;
        }
    }

    private void postDataToSQLLite(){

        if(!inputValidation.isInputEditTextFilled(textInputEditTextName,textInputLayoutName, getString(R.string.error_name))){
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail,textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }
        if(!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword,textInputLayoutConfirmPassword, getString(R.string.error_password_match))){
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

            waitThread = new Thread() {
                @Override
                public void run() {
                    try {
                        int waited = 0;
                        // Splash screen pause time
                        while (waited < 1500) {
                            sleep(100);
                            waited += 100;
                        }
                        finish();
                    } catch (InterruptedException e) {
                        // do nothing
                    } finally {
                        finish();
                    }

                }

            };
            waitThread.start();

            emptyInputEditText();


            // accountIntent.putExtra("password",textInputEditTextPassword.getText().toString().trim());

        }else {
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exits), Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
