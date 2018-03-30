package my.webs2canada.paytrail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import javax.net.ssl.HttpsURLConnection;

import my.webs2canada.ctc.R;
import my.webs2canada.paytrail.fragment.SalaryDetails;
import my.webs2canada.paytrail.model.CalcPresenterImpl;
import my.webs2canada.paytrail.presenter.CalcPresenter;
import my.webs2canada.paytrail.view.CalcView;
import my.webs2canada.paytrail.webs2canada.utils.validation.Validation;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //private CalcPresenter calcPresenter;
    private PopupWindow pw;

    private EditText Hour;
    private EditText Rate;
    private EditText OverTime;
    private EditText StateHoliday;
    private EditText EShour;

    private TextView logout;
    private TextView welcomeName;

    private Button calculate_number;
    private Button reset;

    Double hour;
    Double rate;
    Double overtime;
    Double sholiday;
    Double eshour;
    Boolean test = true;
    Boolean test2 = true;
    Boolean test3 = true;
    Boolean test4 = true;


    Double weekinyear;

    String province;

    private CheckBox OverTimeCheck;
    private CheckBox StateCheck;
    private CheckBox EsCheck;


    private Spinner spinner;

    private RadioGroup radioCalcGroup;
    private RadioButton radioButton;
    private RadioButton radioButton2;

    Validation validation;

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        validation = new Validation();
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.province_spinner);


        //CheckBoxes
        OverTimeCheck = findViewById(R.id.OvertimeCheck);
        StateCheck = findViewById(R.id.StateholidayCheck);
        EsCheck = findViewById(R.id.ESCheck);


        //EditText
        OverTime = findViewById(R.id.Overtime);
        Rate = findViewById(R.id.Rate);
        Hour = findViewById(R.id.Hour);
        StateHoliday = findViewById(R.id.StateHoliday);
        EShour = findViewById(R.id.ESHour);

        //Button
        calculate_number = findViewById(R.id.Calc);
        reset = findViewById(R.id.Reset);

        //Radio Button
        radioCalcGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.weekly);
        radioButton2 = findViewById(R.id.biweekly);

        //welcome & logout
//        welcomeName = findViewById(R.id.loginName);
//        logout = findViewById(R.id.logOut);

        //EditText Visibility
        OverTime.setVisibility(View.GONE);
        StateHoliday.setVisibility(View.GONE);
        EShour.setVisibility(View.GONE);

        OverTime.setEnabled(false);
        OverTime.setFocusableInTouchMode(false);
        OverTime.clearFocus();

        StateHoliday.setEnabled(false);
        StateHoliday.setFocusableInTouchMode(false);
        StateHoliday.clearFocus();

        EShour.setEnabled(false);
        EShour.setFocusableInTouchMode(false);
        EShour.clearFocus();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


//        String nameFromIntent = getIntent().getStringExtra("email");
//
//         welcomeName.setText("Hello " + nameFromIntent);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                switch (v.getId()) {
//                    case R.id.logOut:
//                        // SharedPreferences preferences = getSharedPreferences("Reg", Context.MODE_PRIVATE);
//                        // SharedPreferences.Editor editor = preferences.edit();
//                        //  editor.commit();
//                        finish();
//                        // session.isUserLoggedIn()== false;
//                        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
//                        startActivity(in);
//
//                }
//            }
//        });

        OverTimeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (((CheckBox) v).isChecked()) {

                    validation.showEditTextBox(OverTime);

                } else {

                    validation.hideEditTextBox(OverTime);


                }
            }
        });


        StateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    validation.showEditTextBox(StateHoliday);
                } else {

                    validation.hideEditTextBox(StateHoliday);


                }
            }
        });

        EsCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    validation.showEditTextBox(EShour);
                } else {

                    validation.hideEditTextBox(EShour);


                }
            }
        });



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hour.setText("");
                Rate.setText("");
                OverTime.setText("");
                StateHoliday.setText("");
                EShour.setText("");
            }
        });

        calculate_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (radioButton.isChecked()) {
                    weekinyear = 52.0;
                }
                if (radioButton2.isChecked()) {
                    weekinyear = 26.0;
                }

                test = validation.setMessage(Hour, "Hour");

                test2 = validation.setMessage(Rate, "Rate");

//                test = setMessage(Hour);
                //              test = setMessage(Rate);
//

                if (OverTimeCheck.isChecked()) {
                    test3 = validation.setMessage(OverTime, "OverTime");
                    //test = setMessage(OverTime);
                }
                if (StateCheck.isChecked()) {
                    test4 = validation.setMessage(StateHoliday, "State Holiday");
                    //test = setMessage(StateHoliday);
                }

                if (EsCheck.isChecked()) {
                    test4 = validation.setMessage(EShour, "E/S Hour");
                    //test = setMessage(StateHoliday);
                }

                if (test && test2 && test3 && test4) {
                    openSecondActivity();
                }


            }
        });

    }


    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        super.onDestroy();

    }


    @Override
    public void onBackPressed() {
        // DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
       // finish();

    }



    public void openSecondActivity(){

        hour = Double.valueOf(Hour.getText().toString());
        rate = Double.valueOf(Rate.getText().toString());

        province = spinner.getSelectedItem().toString();

        if(OverTimeCheck.isChecked()) {
            overtime = Double.valueOf(OverTime.getText().toString());
        }else {
            overtime = 0.0;
        }

        if(StateCheck.isChecked()){
            sholiday = Double.valueOf(StateHoliday.getText().toString());
        }else {
            sholiday =0.0;
        }
        if(EsCheck.isChecked()){
            eshour = Double.valueOf(EShour.getText().toString());
        }else {
            eshour =0.0;
        }


//        Bundle bundle = new Bundle();
//
//        bundle.putDouble("hour",hour);
//        bundle.putDouble("overtime",overtime);
//        bundle.putDouble("rate",rate);
//        bundle.putDouble("sholiday",sholiday);
//        bundle.putDouble("weekinyear",weekinyear);
//        bundle.putString("prov",province);
//        SalaryDetails salaryDetails = new SalaryDetails();
//        salaryDetails.setArguments(bundle);

        Intent intent = new Intent(MainActivity.this, TaxAnswer.class);
        intent.putExtra("hour",hour );
        intent.putExtra("overtime", overtime);
        intent.putExtra("rate", rate);
        intent.putExtra("sholiday", sholiday);
        intent.putExtra("weekinyear", weekinyear);
        intent.putExtra("eshour", eshour);
        intent.putExtra("prov", province);
        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
