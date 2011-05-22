package cz.hackathon.programy;

import android.app.Activity;
import android.os.Bundle;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 12:18
 */
public class ActionActivity extends Activity {

        /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_title);
    }
}
