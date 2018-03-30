package my.webs2canada.paytrail;

import android.os.AsyncTask;
import android.text.Editable;
import android.util.Log;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-04.
 */

 public class TaxBrain {


    //SALARYDETAILS

    public double WAGE;
    public double REGULAR_WAGE;
    public double OVERTIME_WAGE;
    public double SICK_WAGE;
    public double STATE_HOLIDAY_WAGE;
    public double GROSS_INCOME;
    public double CPP;
    public double VACATION_PAY;
    public double EMPLOYMENT_INSURANCE;
    public double FEDERAL_TAX =0.0;
    public double PROVISIONAL_TAX=0.0;
    public double TOTAL_DEDUCTION;
    public double NET_AMOUNT;


    //WorkDetails

    public double REGULAR_HOURS;
    public double RATE;
    public double OVERTIME_HOURS;
    public double STATE_HOLIDAY_HOURS;
    public double SICK_HOURS;
    public double WEEKS;

    //ExtraVars
    public double YEAR_INCOME;
    public double DISPLAY_WAGE;
    public double DISPLAY_YEAR_INCOME;
    public double DISPLAY_TOTAL_TAX;


   public double personalTaxCredit;
    public double personalTaxCreditNew;
   public double R = 0.0;
   public double K = 0.0;
   public double V = 0.0;
   public double KP = 0.0;

//    public double hour=0.0;
//    public double rate=0.0;
//    public double overtime=0.0;
//    public double sholiday;
//    public double weekinyear=0.0;
//    public double surtax;
//    public double net;
//    public double ptax;
//
//
//    //Answer
//    public double hourwage=0.0;
//    public double overTimewage=0.0;
//    public double wageR;
//    public double vacation;
//    public double gross;
//    public  double yincome;
//    public double cpp;
//    public double ei;
//    public double tax;
//    public double ftax;
//    public double constantK;
//    public  double taxRate;
//    public double esHour;
//
//    //steps
//
//    public double step1;
//    public double step7;
//    public double step101 = 11809.00;
//    public double step102;
//    public double step103;
//    public double step10;
//    public double step11;
//    public double step13;
//    public double step14;
//    public double step17;
//    public double step18;
//    public double step20;

   public Boolean online = false;


   public String state;



    public void performCalc() {


        // get a reference to the already created main layout
        //LinearLayout mainLayout = (LinearLayout)
        //      findViewById(R.id.main_layout);

        //hour wage


        //double arr[] = new arr[];


        REGULAR_WAGE = REGULAR_HOURS * RATE;
        OVERTIME_WAGE = OVERTIME_HOURS * RATE * 1.5;
        STATE_HOLIDAY_WAGE = STATE_HOLIDAY_HOURS * RATE;
        SICK_WAGE = SICK_HOURS * RATE;

        DISPLAY_WAGE = REGULAR_HOURS * RATE;
        WAGE = REGULAR_WAGE + OVERTIME_WAGE + STATE_HOLIDAY_WAGE + SICK_WAGE;
        VACATION_PAY = WAGE * 0.04;

        GROSS_INCOME = WAGE + VACATION_PAY;

        YEAR_INCOME = GROSS_INCOME * WEEKS;


        CPP = (0.0495 * (YEAR_INCOME - 3500)) / WEEKS;


        if (CPP < 0.0) {
            CPP = 0.0;
        }

        EMPLOYMENT_INSURANCE = (0.0166 * YEAR_INCOME) / WEEKS;

        YEAR_INCOME = YEAR_INCOME - (VACATION_PAY * WEEKS);

        Log.d("YearIncome", Double.toString(YEAR_INCOME));


        if (YEAR_INCOME > 205842) {
            R = 33 / 100;
            K = 20258;
        } else if (YEAR_INCOME > 144489) {
            R = 29 / 100;
            K = 12024;
        } else if (YEAR_INCOME > 93208) {
            R = 26 / 100;
            K = 7690;
        } else if (YEAR_INCOME > 46605) {
            R = 20.5 / 100;
            K = 2563;
        } else if (YEAR_INCOME > 0) {
            R = 0.15;
            K = 0;
        }

        FEDERAL_TAX = (YEAR_INCOME * R) - K;



        personalTaxCredit = (11809 + (CPP * WEEKS + (EMPLOYMENT_INSURANCE * WEEKS)) + 1195) * 0.15;

        FEDERAL_TAX = (FEDERAL_TAX - personalTaxCredit) / WEEKS;



        //Correct Var
        if (FEDERAL_TAX < 0) {
            FEDERAL_TAX = 0;
        }

        if (YEAR_INCOME > 220000) {
            V = 13.16 / 100;
            KP = 7188;
        } else if (YEAR_INCOME > 150000) {
            V = 12.16 / 100;
            KP = 4988;
        } else if (YEAR_INCOME > 85923) {
            V = 11.16 / 100;
            KP = 3488;
        } else if (YEAR_INCOME > 42960) {
            V = 9.15 / 100;
            KP = 1761;
        }
        else if (YEAR_INCOME > 0){
            V = 5.05/100;
            KP = 0;
        }

        PROVISIONAL_TAX = (YEAR_INCOME * V) - KP;

        personalTaxCreditNew = (10354 + (CPP * WEEKS) + (EMPLOYMENT_INSURANCE * WEEKS)) * 0.0505;

        PROVISIONAL_TAX = (PROVISIONAL_TAX - personalTaxCreditNew) / WEEKS;



        //Correct Var
        if (PROVISIONAL_TAX < 0) {
            PROVISIONAL_TAX = 0;
        }

        DISPLAY_TOTAL_TAX = PROVISIONAL_TAX + FEDERAL_TAX;

        TOTAL_DEDUCTION = CPP + EMPLOYMENT_INSURANCE + FEDERAL_TAX + PROVISIONAL_TAX;
        NET_AMOUNT = GROSS_INCOME - TOTAL_DEDUCTION;

        DISPLAY_YEAR_INCOME = NET_AMOUNT * 12;

//        hourwage = hour * rate;
//
//        //overtime
//        if (overtime != 0.0) {
//            overTimewage = overtime * rate * 1.5;
//        }
//
//        wageR = hourwage + overTimewage;
//
//        //state Holiday
//        if (sholiday != 0.0) {
//
//            sholiday = sholiday * rate;
//        }
//
//
//        wageR = wageR + sholiday;
//
//
//        //E/S Hour
//        if(esHour != 0.0){
//            esHour = esHour * rate;
//        }
//
//        wageR = wageR + esHour;
//
//        //vacation pay
//
//        vacation = (wageR) * 0.04;
//
//        //gross pay
//        gross = wageR + vacation;
//
//        //yearly income
//        yincome = gross * weekinyear;
//
//        //cpp
//        cpp = (0.0495 * (yincome - 3500)) / weekinyear;
//
//        if (cpp < 0.0) {
//
//            cpp = 0.0;
//        }
//
//        //ei
//
//        if (rate >= 1000.00) {
//
//            ei = 0.0;
//        } else {
//            ei = (0.0166 * yincome) / weekinyear;
//        }
//
//        //tax
//
////        if (rate >= 1000.00) {
////            tax = 170.05;
////
////        } else {
////            step1 = gross;
////        }
//
//        if (yincome <= 46605.00) {
//
//            step7 = yincome * 0.15;
//        }
//        else if(yincome > 205842.00){
//            step7 = yincome * 0.33;
//            step7 = step7 - 20258;
//
//        }
//          else if (yincome > 144489.00){
//              step7 = yincome * 0.29;
//              step7 = step7 - 12024;
//            }
//         else if (yincome > 93208.00) {
//
//            step7 = yincome * 0.26;
//            step7 = step7 - 7690.00;
//
//        } else {
//            step7 = yincome * 0.205;
//            step7 = step7 - 2563;
//
//        }
//        step102 = cpp * weekinyear;
//        step103 = ei * weekinyear;
//
//        step10 = step101 + step102 + step103 + 1195.00;
//
//        step11 = step10 * 0.15;
//        step13 = step7 - step11;
//
//
//        //federal tax
//        ftax = step13 / weekinyear;
//
//        if(ftax < 0){
//            ftax =0.0;
//        }
//
//        loadSetting(yincome,state);
//
//        tax = (ftax + ptax + cpp + ei);
//
//        if (tax < 0.0) {
//            tax = 0.0;
//        }
//
//        //net
//        net = (gross - tax);
//
//    }
    }

//    public void loadSetting(Double gross, String province) {
//
//        //provisional tax
//
//        if (online) {
//            taxRate = 0.0;
//            constantK = 0.0;
//
//            step14 = yincome * taxRate;
//            step14 = step14 - constantK;
//
//
//        }
//        else {
//
//            if (yincome <= 42960.00) {
//
//                step14 = yincome * 0.0505;
//
//            } else if (yincome > 85923.00) {
//                step14 = yincome * 0.1116;
//                step14 = step14 - 3488.00;
//            } else {
//                step14 = yincome * 0.0915;
//                step14 = step14 - 1761.00;
//            }
//
//            step17 = 10354.00 + step102 + step103;
//
//            step18 = step17 * 0.0505;
//            step20 = step14 - step18;
//
//
//           /* Provincial surtax */
//
//            if (step20 > 5936.00) {
//                surtax = step20 - 5936.00;
//                surtax = surtax * 0.36;
//                surtax = surtax + (4638.00 * 0.2);
//                step20 = step20 + surtax;
//            } else if (step20 > 4638.00) {
//                surtax = step20 * 0.2;
//                step20 = step20 + surtax;
//            }
//
//            ptax = step20 / weekinyear;
//
//            if (ptax < 0) {
//                ptax = 0.0;
//            }
//        }
//    }
}
