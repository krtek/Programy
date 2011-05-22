package cz.hackathon.programy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

/**
 * 
 * @author arcao@arcao.com
 * 
 */
public class ImportActivity extends Activity implements OnCancelListener {
	private final static String TAG = "Programy|ImportActivity";
	private Handler  handler;
	private ImportThread importThread;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		// check if this intent is started via custom scheme link
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			Uri uri = intent.getData();
			Log.i(TAG, "Importing: " + uri.toString() + ".xml");

			importXml(uri.toString() + ".xml");
		} else {
			finish();
		}
	}
	
	private void importXml(String url) {
		handler = new Handler();
		if (importThread != null && importThread.isAlive())
			return;
		
		progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.import_message), false, true, this);
		
		importThread = new ImportThread(url);
		importThread.start();
		
	}
	
	public void onCancel(DialogInterface dialog) {
		dialog.dismiss();
		handler.post(new Runnable() {
			public void run() {
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
			ActionProvider  provider = ProviderFactory.getProvider();
			provider.addXml(url);
			
			handler.post(new Runnable() {
				public void run() {
					progressDialog.dismiss();
					// TODO switch to activity
					
				}
			});
		}
	}
}
