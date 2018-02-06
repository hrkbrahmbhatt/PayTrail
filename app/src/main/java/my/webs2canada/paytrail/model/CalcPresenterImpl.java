package my.webs2canada.paytrail.model;

import android.text.TextUtils;

import my.webs2canada.paytrail.presenter.CalcPresenter;
import my.webs2canada.paytrail.view.CalcView;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-04.
 */

public class CalcPresenterImpl implements CalcPresenter {

    CalcView mcalcView;


    public CalcPresenterImpl(CalcView calcView){
        this.mcalcView=calcView;
    }

    @Override
    public void performValidation(Double hour, Double overtime, Double rate, Double stateholiday) {

        if(hour==null && hour == 0.0){
            mcalcView.hourValidation();
        }
        if(overtime == null && overtime == 0.0){
            mcalcView.overTimeValidation();
        }
        if(rate == null && rate == 0.0){
            mcalcView.rateValidation();
        }
        if (stateholiday==null && stateholiday==0.0){
            mcalcView.stateValidation();
        }

    }
}
