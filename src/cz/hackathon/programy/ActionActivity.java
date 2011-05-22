package cz.hackathon.programy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

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

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        int actionId = i.getIntExtra(ActionProvider.ACTION_ID, 0);


        Action a = ProviderFactory.getProvider().getAction(actionId);
        ((TextView)findViewById(R.id.action_name)).setText(a.name);
        ((TextView)findViewById(R.id.action_description)).setText(a.description);
        ((TextView)findViewById(R.id.action_url)).setText(a.webUrl);
        ((TextView)findViewById(R.id.action_location)).setText(a.locationLat + "," + a.locationLong);
        if (a.imageUrl != null) {
            ((ImageView)findViewById(R.id.imageView1)).setImageURI(Uri.parse(a.imageUrl));
        }

    }
}
