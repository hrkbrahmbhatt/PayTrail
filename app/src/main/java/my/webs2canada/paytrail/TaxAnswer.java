package my.webs2canada.paytrail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import my.webs2canada.ctc.R;

public class TaxAnswer extends AppCompatActivity {


    //Show View
    TextView Hour_Wage;
    TextView OverTime_Wage;
    TextView State_Wage;
    TextView ES_Wage;
    TextView Gross;
    TextView CPP;
    TextView VacationPay;
    TextView EI;
    TextView FederalTax;
    TextView ProvisionalTax;
    TextView TotalDeduction;
    TextView NetAmount;

    TextView Hour;
    TextView Rate;
    TextView OverTimeHour;
    TextView StateHour;
    TextView ESHour;

    Button calculate_number;

    Button shareButton;
    Button cancelButton;


    double statehours;
    double eshour;
    private TaxBrain taxBrain;


    private static final int PERMISSION_REQUEST_CODE = 1;
    final DecimalFormat df = new DecimalFormat("0.00");

    Double hourwage;
    Double overWage;
    Double stateWage;
    Double esWage;
    Double grossTemp;
    Double cppTemp;
    Double vacationpayTemp;
    Double eiTemp;
    Double ftaxTemp;
    Double ptaxTemp;
    Double taxdeductTemp;
    Double netTemp;
    Double rate;
    Double shour;
    Double Ohour;
    Double ehour;
    Double rhour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_answer);


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        } else {

        }


        taxBrain = new TaxBrain();


        // you only need a PdfStamper if you're going to change the existing PDF.

        Bundle b = this.getIntent().getExtras();

        taxBrain.REGULAR_HOURS = b.getDouble("hour");
        taxBrain.OVERTIME_HOURS = b.getDouble("overtime");
        taxBrain.RATE = b.getDouble("rate");
        taxBrain.STATE_HOLIDAY_HOURS = b.getDouble("sholiday");
        taxBrain.WEEKS = b.getDouble("weekinyear");
        taxBrain.state = b.getString("prov");
        statehours = b.getDouble("sholiday");
        taxBrain.SICK_HOURS = b.getDouble("eshour");
        eshour = b.getDouble("eshour");



        taxBrain.performCalc();
        //new LoadCalc().execute();


        //Hour Wage
        Hour_Wage = findViewById(R.id.Hour_Wage);
        hourwage = Double.valueOf(df.format(taxBrain.REGULAR_WAGE));
        Hour_Wage.setText(Double.toString(hourwage));

        //Overtime wage
        OverTime_Wage = findViewById(R.id.Overtime_Wage);
        overWage = Double.valueOf(df.format(taxBrain.OVERTIME_WAGE));
        OverTime_Wage.setText(Double.toString(overWage));

        //State wage
        State_Wage = findViewById(R.id.State_Wage);
        stateWage = Double.valueOf(df.format(taxBrain.STATE_HOLIDAY_WAGE));
        State_Wage.setText(Double.toString(stateWage));

        //ES Wage
        ES_Wage = findViewById(R.id.ES_Wage);
        esWage = Double.valueOf(df.format(taxBrain.SICK_WAGE));
        ES_Wage.setText(Double.toString(esWage));

        //Gross Income
        Gross = (TextView) findViewById(R.id.Gross_Income);
       // String grossTemp = String.format("%1.2f",taxBrain.gross);
        grossTemp = Double.valueOf(df.format(taxBrain.GROSS_INCOME));
        Gross.setText(Double.toString(grossTemp));

        //CPP
        CPP = (TextView) findViewById(R.id.CPP_Plan);
        //double cppTemp = Math.round(cpp);
        cppTemp = Double.valueOf(df.format(taxBrain.CPP));
        CPP.setText(Double.toString(cppTemp));

        //Vacation Pay
        VacationPay = (TextView) findViewById(R.id.Vacation_Pay);
        //double vacationpayTemp = Math.round(vacation);
        vacationpayTemp = Double.valueOf(df.format(taxBrain.VACATION_PAY));
        VacationPay.setText(Double.toString(vacationpayTemp));

        //EI
        EI = (TextView) findViewById(R.id.EI);
        //double eiTemp = Math.round(ei);
        eiTemp = Double.valueOf(df.format(taxBrain.EMPLOYMENT_INSURANCE));
        EI.setText(Double.toString(eiTemp));

        //Federal Tax
        FederalTax = (TextView) findViewById(R.id.Federal_Tax);
        //double ftaxTemp = Math.round(ftax);
        ftaxTemp = Double.valueOf(df.format(taxBrain.DISPLAY_TOTAL_TAX));
        Log.d("Federal Tax", Double.toString(taxBrain.FEDERAL_TAX));
        FederalTax.setText(Double.toString(ftaxTemp));

        //Provisional Tax
        ProvisionalTax = (TextView) findViewById(R.id.Provisional_Tax);
        //double ptaxTemp = Math.round(ptax);
        ptaxTemp = Double.valueOf(df.format(taxBrain.PROVISIONAL_TAX));
        Log.d("Federal Tax", Double.toString(taxBrain.PROVISIONAL_TAX));
        ProvisionalTax.setText(Double.toString(ptaxTemp));

        //Total Deduction
        TotalDeduction = (TextView) findViewById(R.id.Total_Deduction);
        //double taxdeductTemp = Math.round(tax);
        taxdeductTemp = Double.valueOf(df.format(taxBrain.TOTAL_DEDUCTION));
        TotalDeduction.setText(Double.toString(taxdeductTemp));

        //Net
        NetAmount = (TextView) findViewById(R.id.Net_Amount);
        // double netTemp = Math.round(net);
        netTemp = Double.valueOf(df.format(taxBrain.NET_AMOUNT));
        NetAmount.setText(Double.toString(netTemp));


        //Rate
        Rate = findViewById(R.id.Rate1);
        rate = Double.valueOf(df.format(taxBrain.RATE));
        Rate.setText(Double.toString(rate));

        //OverTime
        OverTimeHour = findViewById(R.id.OverTime1);
         Ohour = Double.valueOf(df.format(taxBrain.OVERTIME_HOURS));
        OverTimeHour.setText(Double.toString(Ohour));

        //StateHoli
        StateHour = findViewById(R.id.StateHour1);
        shour = Double.valueOf(df.format(statehours));
        StateHour.setText(Double.toString(shour));

        //ESHour
        ESHour = findViewById(R.id.ESHour);
        ehour = Double.valueOf(df.format(eshour));
        ESHour.setText(Double.toString(ehour));


        //Hour
        Hour = findViewById(R.id.Hour1);
        rhour = Double.valueOf(df.format(taxBrain.REGULAR_HOURS));
        Hour.setText(Double.toString(rhour));


        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(cancel_button);

        shareButton = findViewById(R.id.share_data);
        shareButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               shareIt();

                                           }
                                       });



                Button exportData = findViewById(R.id.export_data);
        exportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new LoadPdf().execute();


//                PdfReader reader;
//                PdfStamper stamper;
//                AcroFields acroFields;
//                Set<String> fldNames;
//
//                try{
//                    File obj=new File(Environment.getExternalStorageDirectory(),"pdf");
//                    obj.mkdirs();
//
//                    if(!obj.exists())
//                    {
//                        obj.mkdirs();
//                        File file = new File(obj,"Paytrail.pdf");
//                        file.createNewFile();
//                    }
//                        String name =obj.getAbsolutePath()+"/Paytrail.pdf";
//
//                try {
//                    try {
//                        reader = new PdfReader(getResources().openRawResource(R.raw.paytrail_pdf));
//                        acroFields = reader.getAcroFields();
//                        fldNames = acroFields.getFields().keySet();
//
//                        for (String fldName : fldNames) {
//                            try {
//                                acroFields.setField("Wage", Double.toString(taxBrain.hourwage));
//                                acroFields.setField("Overtime_Wage", Double.toString(taxBrain.overTimewage));
//                                acroFields.setField("State_Holiday_Wage", Double.toString(taxBrain.sholiday));
//                                acroFields.setField("Gross_Income", Double.toString(taxBrain.gross));
//                                acroFields.setField("CPP_Pension_Plan", Double.toString(taxBrain.cpp));
//                                acroFields.setField("Vacation_Pay", Double.toString(taxBrain.vacation));
//                                acroFields.setField("Employment_Insurance", Double.toString(taxBrain.ei));
//                                acroFields.setField("Federal_Tax", Double.toString(taxBrain.ftax));
//                                acroFields.setField("Provisional_Tax", Double.toString(taxBrain.ptax));
//                                acroFields.setField("Total_Deduction", Double.toString(taxBrain.tax));
//                                acroFields.setField("Net_Amount", Double.toString(taxBrain.net));
//                                acroFields.setField("Regular_Hours", Double.toString(taxBrain.hour));
//                                acroFields.setField("Rate", Double.toString(taxBrain.rate));
//                                acroFields.setField("Overtime_Hours", Double.toString(taxBrain.overtime));
//                                acroFields.setField("Stat_Hours", Double.toString(statehours));
//                                acroFields.setField("ES_Hours", Double.toString(taxBrain.hour));
//
//                            } catch (DocumentException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        try {
//                            stamper = new PdfStamper(reader, new FileOutputStream(name));
//                            stamper.setFormFlattening(true);
//                            stamper.close();
//                            reader.close();
//
//                        }  catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                            } catch (DocumentException e) {
//                                e.printStackTrace();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//            }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
            }
        });

    }



    private class LoadPdf extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        Toast.makeText(getApplicationContext(), "Exported to Paytrail folder in your storage",Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            InputStream src = getResources().openRawResource(R.raw.paytrail_new_format);
            File path = new File(Environment.getExternalStorageDirectory() + "/Paytrail");
            String dst = path.getPath() + '/' + "paytrail " +generateUniqueFileName()+".pdf";

            boolean isDirectoryCreated = path.exists();

            if (!isDirectoryCreated) {
                isDirectoryCreated = path.mkdir();
            }
            if (isDirectoryCreated) {

                try {
                    manipulatePDF(src, dst);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                return "Exported to Paytrail";
        }


        String generateUniqueFileName() {
            String filename = "";
            long millis = System.currentTimeMillis();
            String datetime = new Date().toGMTString();
            datetime = datetime.replace(" ", "");
            datetime = datetime.replace(":", "");
            String rndchars = RandomStringUtils.randomAlphanumeric(16);
            filename =  datetime + "_" + millis + "_" + rndchars;
            return filename;
        }

        private void manipulatePDF(InputStream src, String dst) throws IOException, DocumentException {
            PdfReader reader = new PdfReader(src);
            PdfStamper stamper = new PdfStamper(reader,
                    new FileOutputStream(dst));
            AcroFields acroFields = stamper.getAcroFields();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            acroFields.setField("Date", (dateFormat.format(date)));
            acroFields.setField("Wage", Double.toString(hourwage));
            acroFields.setField("Overtime_Wage", Double.toString(overWage));
            acroFields.setField("State_Holiday_Wage", Double.toString(stateWage));
            acroFields.setField("Gross_Income", Double.toString(grossTemp));
            acroFields.setField("CPP_Pension_Plan", Double.toString(cppTemp));
            acroFields.setField("Vacation_Pay", Double.toString(vacationpayTemp));
            acroFields.setField("Employment_Insurance", Double.toString(eiTemp));
            acroFields.setField("Federal_Tax", Double.toString(ftaxTemp));
            acroFields.setField("Provisional_Tax", Double.toString(ptaxTemp));
            acroFields.setField("Total_Deduction", Double.toString(taxdeductTemp));
            acroFields.setField("Net_Amount", Double.toString(netTemp));
            acroFields.setField("Regular_Hours", Double.toString(rate));
            acroFields.setField("Rate", Double.toString(shour));
            acroFields.setField("Overtime_Hours", Double.toString(Ohour));
            acroFields.setField("Stat_Hours", Double.toString(ehour));
            acroFields.setField("ES_Hours", Double.toString(eshour));

            stamper.close();
            reader.close();

        }

    }



    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(TaxAnswer.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(TaxAnswer.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(TaxAnswer.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(TaxAnswer.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
    private View.OnClickListener cancel_button = new View.OnClickListener() {
        @Override
        public void onClick(View layout) {

            //Intent intent = new Intent(TaxAnswer.this, MainActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, message);
            TaxAnswer.this.finish();
            //startActivity(intent);

        }
    };


    private void shareIt() {
//sharing implementation here

        new LoadPdf().execute();

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "PayTrail Stubs");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "[Add Your Pdf file Here from PayTrail Folder]");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
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


  /*  public void generatePDF(View view) {

        PdfReader reader = null;
        try {
            reader = new PdfReader(getResources().openRawResource(R.raw.paytrail_pdf));
        } catch (IOException e) {
            e.printStackTrace();
        }

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        String outPath = Environment.getExternalStorageDirectory() + "/Paytrail.pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outPath));
            document.open();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outPath));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AcroFields fields = reader.getAcroFields();

        Set<String> fldNames = fields.getFields().keySet();

        for (String fldName : fldNames) {
            System.out.println(fldName + ": " + fields.getField(fldName));
        }

        Base64.OutputStream output = null;
        // PdfReader reader = new PdfReader(getResources().openRawResource(R.raw.template3));
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, output);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AcroFields acroFields = stamper.getAcroFields();

        try {
            acroFields.setField("Wage", Double.toString(taxBrain.hourwage));
            acroFields.setField("Overtime_Wage", Double.toString(taxBrain.overTimewage));
            acroFields.setField("State_Holiday_Wage", Double.toString(taxBrain.sholiday));
            acroFields.setField("Gross_Income", Double.toString(taxBrain.gross));
            acroFields.setField("CPP_Pension_Plan", Double.toString(taxBrain.cpp));
            acroFields.setField("Vacation_Pay", Double.toString(taxBrain.vacation));
            acroFields.setField("Employment_Insurance", Double.toString(taxBrain.ei));
            acroFields.setField("Federal_Tax", Double.toString(taxBrain.ftax));
            acroFields.setField("Provisional_Tax", Double.toString(taxBrain.ptax));
            acroFields.setField("Total_Deduction", Double.toString(taxBrain.tax));
            acroFields.setField("Net_Amount", Double.toString(taxBrain.net));
            acroFields.setField("Regular_Hours", Double.toString(taxBrain.hour));
            acroFields.setField("Rate", Double.toString(taxBrain.rate));
            acroFields.setField("Overtime_Hours", Double.toString(taxBrain.overtime));
            acroFields.setField("Stat_Hours", Double.toString(statehours));
            acroFields.setField("ES_Hours", Double.toString(taxBrain.hour));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        stamper.setFormFlattening(true);
        try {
            stamper.close();
            reader.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


//    class LoadCalc extends AsyncTask<Void,Void,Boolean>{
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//            return taxBrain.performCalc();
//        }
//    }

    }

