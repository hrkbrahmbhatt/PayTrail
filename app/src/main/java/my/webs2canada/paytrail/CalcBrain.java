package my.webs2canada.paytrail;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-04.
 */

public class CalcBrain {

     Double hour = 0.0;
  Double rate = 0.0;
    Double overtime = 0.0;
    Double sholiday = 0.0;
    Double weekinyear = 52.0;
  Double surtax;
     Double net;
 Double ptax;


    //Answer
    Double hourwage = 0.0;
     Double overTimewage = 0.0;
     Double wageR = 0.0;
    Double vacation = 0.0;
    Double gross = 0.0;
    Double yincome;
    Double cpp;
    Double ei;
     Double tax;
    Double ftax;

    //steps

    Double step1;
    Double step7;
    Double step101 = 11809.00;
    Double step102;
    Double step103;
    Double step10;
    Double step11;
    Double step13;
    Double step14;
    Double step17;
    Double step18;
    Double step20;

    public void performCalc() {


        // get a reference to the already created main layout
        //LinearLayout mainLayout = (LinearLayout)
        //      findViewById(R.id.main_layout);

        //hour wage
        hourwage = hour * rate;

        //overtime
        if (overtime != 0.0) {
            overTimewage = overtime * rate * 1.5;
        } else {
            overtime = 0.0;
        }
        wageR = hourwage + overTimewage;

        //state Holiday
        if (sholiday != 0.0) {

            sholiday = sholiday * rate;
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

}
