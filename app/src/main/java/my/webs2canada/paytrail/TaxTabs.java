package my.webs2canada.paytrail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import my.webs2canada.ctc.R;
import my.webs2canada.paytrail.fragment.SalaryDetails;
import my.webs2canada.paytrail.fragment.WorkDetails;

public class TaxTabs extends AppCompatActivity {

    double hour;
    double rate;
    double overtime;
    double sholiday;
    double weekinyear;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle b = this.getIntent().getExtras();

        hour=  b.getDouble("hour");
        overtime= b.getDouble("overtime");
        rate = b.getDouble("rate");
        sholiday = b.getDouble("sholidy");
        weekinyear = b.getDouble("weekinyear");
        state = b.getString("prov");

        Bundle bundle = new Bundle();

        bundle.putDouble("hour",hour);
        bundle.putDouble("overtime",overtime);
        bundle.putDouble("rate",rate);
        bundle.putDouble("sholiday",sholiday);
        bundle.putDouble("weekinyear",weekinyear);
        bundle.putString("prov",state);
        SalaryDetails salaryDetails = new SalaryDetails();
        salaryDetails.setArguments(bundle);


        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new SalaryDetails(), "Salary Details");
        adapter.addFragment(new WorkDetails(), "Work Details");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
