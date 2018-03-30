package my.webs2canada.paytrail.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import my.webs2canada.ctc.R;
import my.webs2canada.paytrail.TaxAnswer;
import my.webs2canada.paytrail.TaxBrain;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkDetails extends Fragment {



    TextView Hour;
    TextView Rate;
    TextView OverTimeHour;
    TextView StateHour;
    TextView ESHour;

    private TaxBrain taxBrain;

    final DecimalFormat df = new DecimalFormat("####0.00");

    public WorkDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//
//        taxBrain = new TaxBrain();
//
//        Bundle b = this.getArguments();
//
//        taxBrain.hour = b.getDouble("hour");
//        taxBrain.overtime = b.getDouble("overtime");
//        taxBrain.rate = b.getDouble("rate");
//        taxBrain.sholiday = b.getDouble("sholidy");
//        taxBrain.weekinyear = b.getDouble("weekinyear");
//        taxBrain.state = b.getString("prov");
//
//
//        taxBrain.performCalc();
//
//        //Rate
//        Rate = getView().findViewById(R.id.Rate1);
//        Double rate = Double.valueOf(df.format(taxBrain.rate));
//        Rate.setText(Double.toString(rate));
//
//        //OverTime
//        OverTimeHour = getView().findViewById(R.id.OverTime1);
//        Double Ohour = Double.valueOf(df.format(taxBrain.overtime));
//        OverTimeHour.setText(Double.toString(Ohour));
//
//        //StateHoli
//        StateHour = getView().findViewById(R.id.StateHour1);
//        Double shour = Double.valueOf(df.format(taxBrain.sholiday));
//        StateHour.setText(Double.toString(shour));
//
//        //ESHour
//        ESHour = getView().findViewById(R.id.ESHour);
//        Double ehour = Double.valueOf(df.format(taxBrain.hour));
//        ESHour.setText(Double.toString(ehour));
//
//
//        //Hour
//        Hour = getView().findViewById(R.id.Hour1);
//        Double rhour = Double.valueOf(df.format(taxBrain.hour));
//        Hour.setText(Double.toString(rhour));
//

    }


}
