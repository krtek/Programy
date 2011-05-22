package cz.hackathon.programy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Festivals extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, FestTabActivity.class);
		startActivity(intent);
		
    }
}