package biophics.mdot.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import biophics.mdot.Fragment.DashboardFragment;
import biophics.mdot.Fragment.HomeFragment;
import biophics.mdot.Fragment.InfoFragment;
import biophics.mdot.Fragment.NotificationsFragment;
import biophics.mdot.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_dot:
                    //mTextMessage.setText(R.string.title_dashboard);
                    loadFragment(new DashboardFragment());
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_info);
                    loadFragment(new NotificationsFragment());
                    return true;
                case R.id.navigation_info:
                    //mTextMessage.setText(R.string.title_notifications);
                    loadFragment(new InfoFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
        //loadFragment();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        return;
    }

}
