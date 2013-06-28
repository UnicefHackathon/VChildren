package unicef.hackathon.isu;

import java.util.ArrayList;



import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ScreamChoose extends Fragment {
	
	private GridviewAdapter mAdapter;
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;
	
	private GridView gridView;
	
	//out in
	
	private View subLayout;
	private View topLayout;
	private ListView subViewListView;
	private String listViewDummyContent[]={"Android","iPhone","BlackBerry","AndroidPeople"};
	private Display display;
	private View fakeLayout;
	private Animation.AnimationListener AL;
	 
	// Values for after the animation
	private int oldLeft;
	private int oldTop;
	private int newleft;
	private int newTop;
	private int screenWidth;
	private int animToPostion;

	private boolean menuOpen = false;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		setHasOptionsMenu(true);
		prepareList();
		//
	     
		//
		View myFragmentView = inflater.inflate(R.layout.activity_screamchoose, container, false);
		
		gridView=(GridView) myFragmentView.findViewById(R.id.gridscreamchoose);
		gridView.setAdapter(new GridviewAdapter(listCountry, listFlag, this.getActivity()));
		//
		subLayout = myFragmentView.findViewById(R.id.layout);
        topLayout = myFragmentView.findViewById(R.id.layoutTwo);
        subViewListView=(ListView) myFragmentView.findViewById(R.id.listView1);
        fakeLayout = myFragmentView.findViewById(R.id.fake_layouy);
        
        display = getActivity().getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        int calcAnimationPosition = (screenWidth /3);

        // Value where the onTop Layer has to animate
        // also the max width of the layout underneath 
        // Set Layout params for subLayout according to calculation
        animToPostion = screenWidth - calcAnimationPosition;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(animToPostion, RelativeLayout.LayoutParams.FILL_PARENT);
        subLayout.setLayoutParams(params);
		
        bindListeners();
	    bindAnimationListeners();
	    initListView();
	    
		return myFragmentView;
		
		
	}
	

	 private void initListView() {
	        subViewListView.setAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1 , listViewDummyContent));
	    }
	
	private void bindListeners() {
        topLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (menuOpen == true) {
                        animSlideLeft();
                    }
                }

                return false;
            }
        });

    }
	
	private void bindAnimationListeners() {
        
		AL = new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                //android.R.id.home.setClickable(false);
                topLayout.setEnabled(false);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(menuOpen == true) {

                    topLayout.layout(oldLeft, oldTop, oldLeft + topLayout.getMeasuredWidth(), oldTop + topLayout.getMeasuredHeight() );
                    menuOpen = false;
                    //buttonSwitch.setClickable(true);
                    topLayout.setEnabled(true);
                } else if(menuOpen == false) {

                    topLayout.layout(newleft, newTop, newleft + topLayout.getMeasuredWidth(), newTop + topLayout.getMeasuredHeight() );
                    topLayout.setEnabled(true);
                    menuOpen = true;
                    //buttonSwitch.setClickable(true);
                }
            }
        };
    }

    private void animSlideRight(){

        fakeLayout.setVisibility(View.VISIBLE);
        newleft = topLayout.getLeft() + animToPostion;
        newTop = topLayout.getTop();
        TranslateAnimation slideRight = new TranslateAnimation(0,newleft,0,0);
        slideRight.setDuration(500);
        slideRight.setFillEnabled(true);
        slideRight.setAnimationListener(AL);
        topLayout.startAnimation(slideRight);
    }

    private void animSlideLeft() {

        fakeLayout.setVisibility(View.GONE);
        oldLeft = topLayout.getLeft() - animToPostion;
        oldTop = topLayout.getTop();
        TranslateAnimation slideLeft = new TranslateAnimation(newleft,oldLeft,0,0);
        slideLeft.setDuration(500);
        slideLeft.setFillEnabled(true);
        slideLeft.setAnimationListener(AL);
        topLayout.startAnimation(slideLeft);
    }

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public void prepareList()
    {
    	  listCountry = new ArrayList<String>();
    	  
    	  listCountry.add("india");
    	  listCountry.add("Brazil");
          listCountry.add("Canada");
          listCountry.add("China");
          listCountry.add("France");
          listCountry.add("Germany");
          listCountry.add("Iran");
          listCountry.add("Italy");
          listCountry.add("Japan");
          listCountry.add("Korea");
          listCountry.add("Mexico");  
          listCountry.add("Netherlands");
          listCountry.add("Portugal");  
          listCountry.add("Russia");
          listCountry.add("Saudi Arabia");  
          listCountry.add("Spain");
          listCountry.add("Turkey");
          listCountry.add("United Kingdom");
          listCountry.add("United States");
          
          listFlag = new ArrayList<Integer>();
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
          listFlag.add(R.drawable.ic_launcher);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		case android.R.id.home:{
			
			if(menuOpen == false){
                animSlideRight();
            } else if (menuOpen == true) {
                animSlideLeft();
            }
			break;
			
			
			}
		}
		
		return super.onOptionsItemSelected(item);
	}
		
}


