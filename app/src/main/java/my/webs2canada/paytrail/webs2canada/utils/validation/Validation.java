package my.webs2canada.paytrail.webs2canada.utils.validation;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by hrsikeshbrahmbhatt on 2018-02-08.
 */

public class Validation extends AppCompatActivity {


    public void showEditTextBox(EditText textBox){



        textBox.setVisibility(View.VISIBLE);
        textBox.setEnabled(true);
        textBox.setFocusableInTouchMode(true);
        textBox.requestFocus();



    }

    public void hideEditTextBox(EditText textBox){

        textBox.setVisibility(View.GONE);
        textBox.setText("");
        textBox.setEnabled(false);
        textBox.setFocusableInTouchMode(false);
        textBox.clearFocus();


    }


    public Boolean setMessage(EditText editText, String msg) {

        if (editText.getText().toString().endsWith(".") || editText.getText().toString().length() == 0.0 || editText.getText().toString().equals("") || 0 == Double.parseDouble(editText.getText().toString()))  {

            // Rate.setError("Hours Required");
            //String error = getResources().getResourceEntryName(editText.getId());
            //String error;
            msg = msg.concat(" is Required");
            editText.setError(msg);

            return false;
        }
        return true;
    }

    public void callMethods(){


    }



}
