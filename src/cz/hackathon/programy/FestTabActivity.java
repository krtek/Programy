package cz.hackathon.programy;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

public class FestTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.festtabs);

        Intent i = getIntent();

        ActionProvider data = ProviderFactory.getProvider(this);

		int actionId = i.getExtras().getInt(ActionProvider.ACTION_ID);

		Action action = data.getAction(actionId);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		{
			// // Do the same for the other tabs
			 intent = new Intent().setClass(this, ActionActivity.class);
			 intent.putExtra(ActionProvider.ACTION_ID, actionId);
			 spec = tabHost.newTabSpec("albums").
			 setIndicator("Detail", res.getDrawable(android.R.drawable.ic_menu_gallery))
			 .setContent(intent);
			 tabHost.addTab(spec);
		}

		int stageId = 0;
		for (Stage stage : action.stages) {
			// Initialize a TabSpec for each tab and add it to the TabHost
			// Create an Intent to launch an Activity for the tab (to be reused)
			intent = new Intent().setClass(this, StageActivity.class);
			 intent.putExtra(ActionProvider.ACTION_ID, actionId);
			 intent.putExtra(ActionProvider.STAGE_ID, stageId);
			spec = tabHost.newTabSpec("artists"+stageId).
			setIndicator(stage.name, res.getDrawable(android.R.drawable.ic_lock_silent_mode_off)).setContent(intent);
			tabHost.addTab(spec);
			stageId++;

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
