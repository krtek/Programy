package cz.hackathon.programy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

/**
 * Importuje akce z XML souboru
 * 
 * @author arcao@arcao.com
 * 
 */
public class ImportActivity extends Activity implements OnCancelListener {
	private final static String TAG = "Festacky|ImportActivity";
	private Handler handler;
	private ImportThread importThread;
	private ProgressDialog progressDialog;
	private Resources res;
	private ActionProvider provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		provider = ProviderFactory.getProvider(this);
		handler = new Handler();
		res = getResources();

		Intent intent = getIntent();
		// check if this intent is started via custom scheme link
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			Uri uri = intent.getData();

			String url = uri.toString() + ".xml";

			Log.i(TAG, "Importing: " + url);
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
				importXml(url);
			}
		} else {
			finish();
		}
	}
	
	private void importXml(String url) {
		if (importThread != null && importThread.isAlive())
			return;

		progressDialog = ProgressDialog.show(this, null,
				res.getString(R.string.import_message), true, true, this);

		importThread = new ImportThread(url);
		importThread.start();
	}

	public void onCancel(DialogInterface dialog) {
		handler.post(new Runnable() {
			public void run() {
				progressDialog.dismiss();
				finish();
			}
		});
	}

	private class ImportThread extends Thread {
		private String url;

		public ImportThread(String url) {
			this.url = url;
		}

		@Override
		public void run() {
			// ActionProvider provider = ProviderFactory.getProvider();
			provider.addXml(url);

			handler.post(new Runnable() {
				public void run() {
					progressDialog.dismiss();

					// TODO switch to Festivals
					final Intent intent = new Intent(ImportActivity.this,
							ListActionActivity.class);
					startActivity(intent);

					finish();
				}
			});
		}
	}
}
