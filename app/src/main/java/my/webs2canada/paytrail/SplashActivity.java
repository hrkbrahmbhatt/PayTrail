package my.webs2canada.paytrail;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import my.webs2canada.ctc.R;


//Extra Task from me Intro Screen Shopify

public class SplashActivity extends AppCompatActivity {


    Thread splashThread;

    public void onAttachedToWindow() {

        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        startAnimation();
    }

    // Transition Animation
    private void startAnimation() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin_lay);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        animation.reset();
        ImageView imageView = (ImageView) findViewById(R.id.splash);
        imageView.clearAnimation();
        imageView.startAnimation(animation);
        TextView textView = (TextView) findViewById(R.id.shopify);
        textView.clearAnimation();
        textView.startAnimation(animation);

        splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }

                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }

        };
        splashThread.start();
    }

    //Disable Back Button
    @Override
    public void onBackPressed() {

       //do nothing
    }

}
