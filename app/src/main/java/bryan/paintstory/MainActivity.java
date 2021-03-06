package bryan.paintstory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private AHBottomNavigation bottomNavigation;
    public static ArrayList<FingerPath> savedPaths = new ArrayList<>();
    public static ArrayList<FingerPath> savedUndonePaths = new ArrayList<>();
    private PaintFragment paintFragment = new PaintFragment();
    private LiveStoryFragment liveStoryFragment = new LiveStoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            paintFragment = (PaintFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, "myPaintFragment");
        }

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "myPaintFragment", paintFragment);
    }
    /**
     * Creates bottom navigation items
     */
    public void createNavItems() {
        AHBottomNavigationItem StoryItem =
                new AHBottomNavigationItem("Story", R.drawable.ic_dashboard_black_24dp);
        AHBottomNavigationItem PaintItem =
                new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp);

        //add them to bar
        bottomNavigation.addItem(StoryItem);
        bottomNavigation.addItem(PaintItem);

        //set properties
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        //set current item
        bottomNavigation.setCurrentItem(0);
    }

    /**
     * Bottom bar click listener that switches fragments
     *
     * @param position    the position that was clicked
     * @param wasSelected true if position was selected
     * @return
     */
    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (position == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_id, liveStoryFragment).commit();
            return true;
        } else if (position == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_id, paintFragment).commit();
            return true;
        }

        return false;
    }
}
