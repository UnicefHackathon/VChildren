package unicef.hackathon.isu;



import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Home extends Activity {
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homescream);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(R.drawable.device_access_storage);
		        
        //ActionBar
		
		final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setDisplayShowTitleEnabled(false);  
        //actionBar.setDisplayShowHomeEnabled(false);
        
        Tab tabA = actionBar.newTab();
        tabA.setIcon(R.drawable.content_event);
        tabA.setTabListener(new TabListener<ScreamChoose>(this, "Tag A", ScreamChoose.class));
        actionBar.addTab(tabA);
        
        Tab tabB = actionBar.newTab();
        tabB.setIcon(R.drawable.social_cc_bcc);
        tabB.setTabListener(new TabListener<ScreamVChildren>(this, "Tag B", ScreamVChildren.class));
        actionBar.addTab(tabB);
        
        Tab tabC = actionBar.newTab();
        tabC.setIcon(R.drawable.social_person);
        tabC.setTabListener(new TabListener<ScreamUser>(this, "Tag C", ScreamUser.class));
        actionBar.addTab(tabC);
        
        if (savedInstanceState != null) {
            int savedIndex = savedInstanceState.getInt("SAVED_INDEX");
            getActionBar().setSelectedNavigationItem(savedIndex);
        }
	}
	
		
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("SAVED_INDEX", getActionBar().getSelectedNavigationIndex());
	}
	
	public static class TabListener<T extends Fragment>implements ActionBar.TabListener{

		private final Activity myActivity;
		private final String myTag;
		private final Class<T> myClass;

	public TabListener(Activity activity, String tag, Class<T> cls) {
		myActivity = activity;
		myTag = tag;
		myClass = cls;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		Fragment myFragment = myActivity.getFragmentManager().findFragmentByTag(myTag);
	
		// Check if the fragment is already initialized
		if (myFragment == null) {
			// If not, instantiate and add it to the activity
			myFragment = Fragment.instantiate(myActivity, myClass.getName());
			ft.add(android.R.id.content, myFragment, myTag);
		} else {
			// If it exists, simply attach it in order to show it
			ft.attach(myFragment);
		}
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	
		Fragment myFragment = myActivity.getFragmentManager().findFragmentByTag(myTag);
	
		if (myFragment != null) {
			// Detach the fragment, because another one is being attached
			ft.detach(myFragment);
		}
	
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
		}

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}
