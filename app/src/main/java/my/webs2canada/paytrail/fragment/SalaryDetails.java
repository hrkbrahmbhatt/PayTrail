package my.webs2canada.paytrail.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import my.webs2canada.ctc.R;
import my.webs2canada.paytrail.TaxBrain;


public class SalaryDetails extends Fragment {


//    //Show View
//    TextView Gross;
//    TextView CPP;
//    TextView VacationPay;
//    TextView EI;
//    TextView FederalTax;
//    TextView ProvisionalTax;
//    TextView TotalDeduction;
//    TextView NetAmount;
//    private TaxBrain taxBrain;
//
//    final DecimalFormat df = new DecimalFormat("####0.00");
//
//    public SalaryDetails() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        taxBrain = new TaxBrain();
//
//        Bundle b = this.getArguments();
//
//        taxBrain.hour=  b.getDouble("hour");
//        taxBrain.overtime= b.getDouble("overtime");
//        taxBrain.rate = b.getDouble("rate");
//        taxBrain.sholiday = b.getDouble("sholidy");
//        taxBrain.weekinyear = b.getDouble("weekinyear");
//        taxBrain.state = b.getString("prov");
//
//        taxBrain.performCalc();
//
//
//        Gross = (TextView) getView().findViewById(R.id.Gross_Income);
//        Double grossTemp = Double.valueOf(df.format(taxBrain.gross));
//        Gross.setText(Double.toString(grossTemp));
//
//        //CPP
//        CPP = (TextView) getView().findViewById(R.id.CPP_Plan);
//        //double cppTemp = Math.round(cpp);
//        Double cppTemp = Double.valueOf(df.format(taxBrain.cpp));
//        CPP.setText(Double.toString(cppTemp));
//
//        //Vacation Pay
//        VacationPay = (TextView) getView().findViewById(R.id.Vacation_Pay);
//        //double vacationpayTemp = Math.round(vacation);
//        Double vacationpayTemp = Double.valueOf(df.format(taxBrain.vacation));
//        VacationPay.setText(Double.toString(vacationpayTemp));
//
//        //EI
//        EI = (TextView) getView().findViewById(R.id.EI);
//        //double eiTemp = Math.round(ei);
//        Double eiTemp = Double.valueOf(df.format(taxBrain.ei));
//        EI.setText(Double.toString(eiTemp));
//
//        //Federal Tax
//        FederalTax = (TextView) getView().findViewById(R.id.Federal_Tax);
//        //double ftaxTemp = Math.round(ftax);
//        Double ftaxTemp = Double.valueOf(df.format(taxBrain.ftax));
//        FederalTax.setText(Double.toString(ftaxTemp));
//
//        //Provisional Tax
//        ProvisionalTax = (TextView) getView().findViewById(R.id.Provisional_Tax);
//        //double ptaxTemp = Math.round(ptax);
//        Double ptaxTemp = Double.valueOf(df.format(taxBrain.ptax));
//        ProvisionalTax.setText(Double.toString(ptaxTemp));
//
//        //Total Deduction
//        TotalDeduction = (TextView) getView().findViewById(R.id.Total_Deduction);
//        //double taxdeductTemp = Math.round(tax);
//        Double taxdeductTemp = Double.valueOf(df.format(taxBrain.tax));
//        TotalDeduction.setText(Double.toString(taxdeductTemp));
//
//        //Net
//        NetAmount = (TextView) getView().findViewById(R.id.Net_Amount);
//        // double netTemp = Math.round(net);
//        Double netTemp = Double.valueOf(df.format(taxBrain.net));
//        NetAmount.setText(Double.toString(netTemp));
//
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salary_details, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






    }
}