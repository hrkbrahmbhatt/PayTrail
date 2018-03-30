package my.webs2canada.paytrail.view;

import my.webs2canada.paytrail.presenter.CalcPresenter;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-04.
 */

public interface CalcView {


    //    void hourValidation();
//    void overTimeValidation();
//    void rateValidation();
//    void stateValidation();

    void setValue(double hour, double rate, double overtime, double sholiday );

}
