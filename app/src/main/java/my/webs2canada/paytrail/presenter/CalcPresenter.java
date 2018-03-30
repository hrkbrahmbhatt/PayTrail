package my.webs2canada.paytrail.presenter;

import my.webs2canada.paytrail.view.CalcView;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-04.
 */

public interface CalcPresenter {



  //  void performValidation(Double hour, Double overtime, Double rate, Double stateholiday);

    void performCalculator();
    void onBind(CalcView calcView);
    void onUnBind();
}
