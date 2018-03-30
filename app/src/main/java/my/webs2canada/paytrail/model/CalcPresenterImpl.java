package my.webs2canada.paytrail.model;

import android.text.TextUtils;

import my.webs2canada.paytrail.presenter.CalcPresenter;
import my.webs2canada.paytrail.view.CalcView;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-04.
 */

public class CalcPresenterImpl implements CalcPresenter {

    CalcView mcalcView;
    double hour=0.0;
    double rate=0.0;
    double overtime=0.0;
    double sholiday;
    double weekinyear = 52.0;
    double surtax;
    double net;
    double ptax;


    //Answer
    double hourwage=0.0;
    double overTimewage=0.0;
    double wageR;
    double vacation;
    double gross;
    double yincome;
    double cpp;
    double ei;
    double tax;
    double ftax;

    //steps

    double step1;
    double step7;
    double step101 = 11809.00;
    double step102;
    double step103;
    double step10;
    double step11;
    double step13;
    double step14;
    double step17;
    double step18;
    double step20;


    public CalcPresenterImpl(CalcView calcView){

        this.mcalcView=calcView;
    }


//   public CalcPresenterImpl(CalcView calcView){
//        this.mcalcView=calcView;
//    }
//
//    @Override
//    public void performValidation(Double hour, Double overtime, Double rate, Double stateholiday) {
//
//        if(hour==null && hour == 0.0){
//            mcalcView.hourValidation();
//        }
//        if(overtime == null && overtime == 0.0){
//            mcalcView.overTimeValidation();
//        }
//        if(rate == null && rate == 0.0){
//            mcalcView.rateValidation();
//        }
//        if (stateholiday==null && stateholiday==0.0){
//            mcalcView.stateValidation();
//        }
//
//    }



    @Override
    public void performCalculator() {


        hourwage = hour * rate;

        //overtime
        if (overtime != 0.0) {
            overTimewage = overtime * rate * 1.5;
        } else {
            overtime = 0.0;
            overTimewage=0.0;
        }
        wageR = hourwage + overTimewage;

        //state Holiday
        if (sholiday != 0.0) {

            sholiday = sholiday * rate;
        }else {
            sholiday=0.0;
        }

        //vacation pay

        vacation = (wageR + sholiday) * 0.04;

        //gross pay
        gross = wageR + vacation + sholiday;

        //yearly income
        yincome = gross * weekinyear;

        //cpp
        cpp = (0.0495 * (yincome - 3500)) / weekinyear;

        if (cpp < 0.0) {

            cpp = 0.0;
        }

        //ei

        if (rate >= 1000.00) {

            ei = 0.0;
        } else {
            ei = (0.0166 * yincome) / weekinyear;
        }

        //tax

        if (rate >= 1000.00) {
            tax = 170.05;

        } else {
            step1 = gross;
        }

        if (yincome <= 46605.00) {

            step7 = yincome * 0.15;

        } else if (yincome > 93208.00) {

            step7 = yincome * 0.26;
            step7 = step7 - 7690.00;

        } else {
            step7 = yincome * 0.205;
            step7 = step7 - 2563;

        }
        step102 = cpp * weekinyear;
        step103 = ei * weekinyear;

        step10 = step101 + step102 + step103 + 1195.00;

        step11 = step10 * 0.15;
        step13 = step7 - step11;


        //federal tax
        ftax = step13 / weekinyear;

        //provisional tax

        if (yincome <= 42960.00) {

            step14 = yincome * 0.0505;

        } else if (yincome > 85923.00) {
            step14 = yincome * 0.1116;
            step14 = step14 - 3488.00;
        } else {
            step14 = yincome * 0.0915;
            step14 = step14 - 1761.00;
        }

        step17 = 10354.00 + step102 + step103;

        step18 = step17 * 0.0505;
        step20 = step14 - step18;


           /* Provincial surtax */

        if (step20 > 5936.00) {
            surtax = step20 - 5936.00;
            surtax = surtax * 0.36;
            surtax = surtax + (4638.00 * 0.2);
            step20 = step20 + surtax;
        } else if (step20 > 4638.00) {
            surtax = step20 * 0.2;
            step20 = step20 + surtax;
        }

        ptax = step20 / weekinyear;

        tax = (ftax + ptax + cpp + ei);

        if (tax < 0.0) {
            tax = 0.0;
        }
        //net
        net = (gross - tax);


    }

    @Override
    public void onBind(CalcView calcView) {

        this.mcalcView=calcView;

    }

    @Override
    public void onUnBind() {
        this.mcalcView=null;

    }



    }
