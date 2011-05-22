package cz.hackathon.programy;

import android.app.Activity;
import android.content.DialogInterface;
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
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

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

        final Action a = ProviderFactory.getProvider().getAction(actionId);
        ((TextView) findViewById(R.id.action_name)).setText(a.name);
        ((TextView) findViewById(R.id.action_description)).setText(a.description);
        ((TextView) findViewById(R.id.action_url)).setText(a.webUrl);
        ((Button) findViewById(R.id.action_location)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String uri = "geo:"+ a.locationLat + "," + a.locationLong;
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
        if (a.imageUrl != null) {
            HttpClient httpClient = new DefaultHttpClient();
            Log.d(ActionActivity.class.getName(), "Loading image from: " + a.imageUrl);
            HttpGet httpGet = new HttpGet(a.imageUrl);
            HttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                InputStream content = response.getEntity().getContent();
                Drawable d = Drawable.createFromStream(content, "src");
                ((ImageView) findViewById(R.id.imageView1)).setImageDrawable(d);
                content.close();
                httpGet.abort();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}
