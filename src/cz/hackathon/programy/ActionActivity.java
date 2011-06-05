package cz.hackathon.programy;

import java.io.IOException;
import java.io.InputStream;

import android.os.Handler;
import cz.hackathon.programy.utils.ImageLoader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
	private final static String TAG = "Festacky|ActionActivity";
    private Handler handler = new Handler();

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

        final Action a = ProviderFactory.getProvider(this).getAction(actionId);
        ((TextView) findViewById(R.id.action_name)).setText(a.name);
        ((TextView) findViewById(R.id.action_description)).setText(a.description);
        ((TextView) findViewById(R.id.action_url)).setText(a.webUrl);
        ((Button) findViewById(R.id.action_location)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String uri = "geo:"+ a.locationLat + "," + a.locationLong;
                Log.i(TAG, "Running geo application, uri: " + uri);
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
        if (a.imageUrl != null) {
            final ImageView logo = (ImageView)findViewById(R.id.imageView1);
            ImageLoader.getInstance().loadImage(a.imageUrl, logo, handler);
        }

    }
}
