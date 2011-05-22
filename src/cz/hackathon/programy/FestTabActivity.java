package cz.hackathon.programy;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class FestTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.festtabs); 

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, StageActivity.class);

		for (int i = 0; i < 5; i++) {
			// Initialize a TabSpec for each tab and add it to the TabHost
			spec = tabHost.newTabSpec("artists").setIndicator("Stage"+i).setContent(intent);
			tabHost.addTab(spec);
		}
		// // Do the same for the other tabs
		// intent = new Intent().setClass(this, AlbumsActivity.class);
		// spec = tabHost.newTabSpec("albums").setIndicator("Albums",
		// res.getDrawable(R.drawable.ic_tab_albums))
		// .setContent(intent);
		// tabHost.addTab(spec);
		//
		// intent = new Intent().setClass(this, SongsActivity.class);
		// spec = tabHost.newTabSpec("songs").setIndicator("Songs",
		// res.getDrawable(R.drawable.ic_tab_songs))
		// .setContent(intent);
		// tabHost.addTab(spec);

		// tabHost.setCurrentTab(2);

	}

}
