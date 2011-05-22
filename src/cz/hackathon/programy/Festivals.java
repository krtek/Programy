package cz.hackathon.programy;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.provider.ActionProvider;

public class Festivals extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

//		DomParser parser = new DomParser("http://programy.arcao.com/akce/1.xml");
//		List<Action> actions = parser.parse();
//		
//		for (Action action : actions) {
//			Log.d("FEST", action.toString());
//		}
		
 
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, FestTabActivity.class);
		intent.putExtra(ActionProvider.ACTION_ID, 0);
		startActivity(intent);
		
   }
}