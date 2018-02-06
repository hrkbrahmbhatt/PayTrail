package my.webs2canada.paytrail;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import my.webs2canada.ctc.R;
import my.webs2canada.paytrail.model.CalcPresenterImpl;
import my.webs2canada.paytrail.presenter.CalcPresenter;
import my.webs2canada.paytrail.view.CalcView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    //CalcPresenter mcalcPresenter;

    private PopupWindow pw;

    EditText Hour;
    EditText Rate;
    EditText OverTime;
    EditText StateHoliday;

    //Show View
    TextView Gross;
    TextView CPP;
    TextView VacationPay;
    TextView EI;
    TextView FederalTax;
    TextView ProvisionalTax;
    TextView TotalDeduction;
    TextView NetAmount;


//    Double hour = 0.0;
//    Double rate = 0.0;
//    Double overtime = 0.0;
//    Double sholiday = 0.0;
//    Double weekinyear = 52.0;
//    Double surtax;
//    Double ptax;
//    Double net;


//    //Answer
//    Double hourwage = 0.0;
//    Double overTimewage = 0.0;
//    Double wageR = 0.0;
//    Double vacation = 0.0;
//    Double gross = 0.0;
//    Double yincome;
//    Double cpp;
//    Double ei;
//    Double tax;
//    Double ftax;

    //steps

//    Double step1;
//    Double step7;
//    Double step101 = 11809.00;
//    Double step102;
//    Double step103;
//    Double step10;
//    Double step11;
//    Double step13;
//    Double step14;
//    Double step17;
//    Double step18;
//    Double step20;

    Button calculate_number;

    private CalcBrain calcBrain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calcBrain = new CalcBrain();
       // mcalcPresenter = new CalcPresenterImpl(MainActivity.this);

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        OverTime = (EditText) findViewById(R.id.Overtime_Box);
        Rate = (EditText) findViewById(R.id.Rate_Box);
        Hour = (EditText) findViewById(R.id.Hour_Box);
        StateHoliday = (EditText) findViewById(R.id.state_holiday);
        calculate_number = (Button) findViewById(R.id.Calc);
        OverTime.setEnabled(false);
        OverTime.setFocusableInTouchMode(false);
        OverTime.clearFocus();

        // DecimalFormat percentageFormat = new DecimalFormat("00.00");
        final DecimalFormat df = new DecimalFormat("####0.00");
        //final String finalPercentage = percentageFormat.format(gross);


        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (((CheckBox) v).isChecked()) {

                    OverTime.setEnabled(true);
                    OverTime.setFocusableInTouchMode(true);
                    OverTime.requestFocus();

                } else {

                    OverTime.setEnabled(false);
                    OverTime.setFocusableInTouchMode(false);
                    OverTime.clearFocus();


                }
            }
        });

        calculate_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Hour.getText().toString().length() == 0.0 && Hour.getText().toString().equals("")) {

                    Hour.setError("Hour is Required");

                } else {
                   calcBrain.gross = Double.parseDouble(Hour.getText().toString());
                }
                if (checkBox1.isChecked()) {
                    if (OverTime.getText().toString().length() == 0.0 && OverTime.getText().toString().equals("")) {
                        OverTime.setError("OverTime is Required");
                    } else {
                        calcBrain.overtime = Double.parseDouble(OverTime.getText().toString());

                    }
                } else {
                    calcBrain.overtime = 0.0;
                    OverTime.setError(null);
                }

                if (Rate.getText().toString().length() == 0.0 && Rate.getText().toString().equals("")) {
                    Rate.setError("Rate is Required");

                } else {
                    calcBrain.rate = Double.parseDouble(Rate.getText().toString());

                }

                if (StateHoliday.getText().length() == 0.0 && StateHoliday.getText().toString().equals("")) {

                    StateHoliday.setError("State Holiday is Required");

                } else {
                    calcBrain.sholiday = Double.parseDouble(StateHoliday.getText().toString());

                }


//                // get a reference to the already created main layout
                //LinearLayout mainLayout = (LinearLayout)
                //      findViewById(R.id.main_layout);

//                //hour wage
//                hourwage = hour * rate;
//
//                //overtime
//                if (overtime != 0.0) {
//                    overTimewage = overtime * rate * 1.5;
//                } else {
//                    overtime = 0.0;
//                }
//                wageR = hourwage + overTimewage;
//
//                //state Holiday
//                if (sholiday != 0.0) {
//
//                    sholiday = sholiday * rate;
//                }
//
//                //vacation pay
//
//                vacation = (wageR + sholiday) * 0.04;
//
//                //gross pay
//
//                gross = wageR + vacation + sholiday;
//
//                //yearly income
//                yincome = gross * weekinyear;
//
//                //cpp
//                cpp = (0.0495 * (yincome - 3500)) / weekinyear;
//
//                if (cpp < 0.0) {
//
//                    cpp = 0.0;
//                }
//
//                //ei
//
//                if (rate >= 1000.00) {
//
//                    ei = 0.0;
//                } else {
//                    ei = (0.0166 * yincome) / weekinyear;
//                }
//
//                //tax
//
//                if (rate >= 1000.00) {
//                    tax = 170.05;
//
//                } else {
//                    step1 = gross;
//                }
//
//                if (yincome <= 46605.00) {
//
//                    step7 = yincome * 0.15;
//
//                } else if (yincome > 93208.00) {
//
//                    step7 = yincome * 0.26;
//                    step7 = step7 - 7690.00;
//
//                } else {
//                    step7 = yincome * 0.205;
//                    step7 = step7 - 2563;
//
//                }
//                step102 = cpp * weekinyear;
//                step103 = ei * weekinyear;
//
//                step10 = step101 + step102 + step103 + 1195.00;
//
//                step11 = step10 * 0.15;
//                step13 = step7 - step11;
//
//
//                //federal tax
//                ftax = step13 / weekinyear;
//
//                //provisional tax
//
//                if (yincome <= 42960.00) {
//
//                    step14 = yincome * 0.0505;
//
//                } else if (yincome > 85923.00) {
//                    step14 = yincome * 0.1116;
//                    step14 = step14 - 3488.00;
//                } else {
//                    step14 = yincome * 0.0915;
//                    step14 = step14 - 1761.00;
//                }
//
//                step17 = 10354.00 + step102 + step103;
//
//                step18 = step17 * 0.0505;
//                step20 = step14 - step18;
//
//
//           /* Provincial surtax */
//
//                if (step20 > 5936.00) {
//                    surtax = step20 - 5936.00;
//                    surtax = surtax * 0.36;
//                    surtax = surtax + (4638.00 * 0.2);
//                    step20 = step20 + surtax;
//                } else if (step20 > 4638.00) {
//                    surtax = step20 * 0.2;
//                    step20 = step20 + surtax;
//                }
//
//                ptax = step20 / weekinyear;
//
//                tax = (ftax + ptax + cpp + ei);
//
//                if (tax < 0.0) {
//                    tax = 0.0;
//                }
//
//                //net
//                net = (gross - tax);
                calcBrain.performCalc();
//

                if (Hour.getText().toString().length() != 0.0 && OverTime.getText().toString().length() != 0.0 && Rate.getText().toString().length() != 0.0 && StateHoliday.getText().toString().length() != 0.0) {
                    try {

                        //We need to get the instance of the LayoutInflater, use the context of this activity
                        LayoutInflater inflater = (LayoutInflater) MainActivity.this
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        //Inflate the view from a predefined XML layout
                        final View layout = inflater.inflate(R.layout.tax_calculation,
                                (ViewGroup) findViewById(R.id.popup_window));

                        pw = new PopupWindow(MainActivity.this);
                        pw.setContentView(layout);

                        pw.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
                        pw.setHeight(LinearLayout.LayoutParams.FILL_PARENT);
                        pw.isFocusable();
                        //pw = new PopupWindow(layout, 1000, 1000, true);
                        // display the popup in the center
                        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

                        //Gross Income
                        Gross = (TextView) layout.findViewById(R.id.Gross_Income);
                        Double grossTemp = Double.valueOf(df.format(calcBrain.gross));
                        Gross.setText(Double.toString(grossTemp));

                        //CPP
                        CPP = (TextView) layout.findViewById(R.id.CPP_Plan);
                        //double cppTemp = Math.round(cpp);
                        Double cppTemp = Double.valueOf(df.format(calcBrain.cpp));
                        CPP.setText(Double.toString(cppTemp));

                        //Vacation Pay
                        VacationPay = (TextView) layout.findViewById(R.id.Vacation_Pay);
                        //double vacationpayTemp = Math.round(vacation);
                        Double vacationpayTemp = Double.valueOf(df.format(calcBrain.vacation));
                        VacationPay.setText(Double.toString(vacationpayTemp));

                        //EI
                        EI = (TextView) layout.findViewById(R.id.EI);
                        //double eiTemp = Math.round(ei);
                        Double eiTemp = Double.valueOf(df.format(calcBrain.ei));
                        EI.setText(Double.toString(eiTemp));

                        //Federal Tax
                        FederalTax = (TextView) layout.findViewById(R.id.Federal_Tax);
                        //double ftaxTemp = Math.round(ftax);
                        Double ftaxTemp = Double.valueOf(df.format(calcBrain.ftax));
                        FederalTax.setText(Double.toString(ftaxTemp));

                        //Provisional Tax
                        ProvisionalTax = (TextView) layout.findViewById(R.id.Provisional_Tax);
                        //double ptaxTemp = Math.round(ptax);
                        Double ptaxTemp = Double.valueOf(df.format(calcBrain.ptax));
                        ProvisionalTax.setText(Double.toString(ptaxTemp));

                        //Total Deduction
                        TotalDeduction = (TextView) layout.findViewById(R.id.Total_Deduction);
                        //double taxdeductTemp = Math.round(tax);
                        Double taxdeductTemp = Double.valueOf(df.format(calcBrain.tax));
                        TotalDeduction.setText(Double.toString(taxdeductTemp));

                        //Net
                        NetAmount = (TextView) layout.findViewById(R.id.Net_Amount);
                        // double netTemp = Math.round(net);
                        Double netTemp = Double.valueOf(df.format(calcBrain.net));
                        NetAmount.setText(Double.toString(netTemp));


                        Button cancelButton = (Button) layout.findViewById(R.id.cancel_button);
                        cancelButton.setOnClickListener(cancel_button);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }

            private View.OnClickListener cancel_button = new View.OnClickListener() {
                @Override
                public void onClick(View layout) {
                    pw.dismiss();
                }
            };


        });


    }


        @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (pw != null) {
            pw.dismiss();
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

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
