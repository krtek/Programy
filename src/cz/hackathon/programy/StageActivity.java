package cz.hackathon.programy;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import cz.hackathon.programy.dto.Stage;
import cz.hackathon.programy.dto.StageEvent;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

public class StageActivity extends ListActivity {

	List<StageEvent> list;
	Stage stage;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.stage_detail);

		Intent i = getIntent();
//		i.getExtras().getInt(ActionProvider.ACTION_ID);

		this.stage = ProviderFactory.getProvider(this).getStages(
				i.getExtras().getInt(ActionProvider.ACTION_ID),
				i.getExtras().getInt(ActionProvider.STAGE_ID));

		this.list = stage.events;

		registerForContextMenu(getListView());

		((TextView) findViewById(R.id.stageDescription)).setText(stage.desc);

		final LayoutInflater inflater = getLayoutInflater();

		ListAdapter adapter = new StageAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Log.e("getView", "pos " + position + " cv " + convertView
						+ " pra " + parent);
				convertView = inflater.inflate(R.layout.event_list_item, null);
				StageEvent event = list.get(position);
				((TextView) convertView.findViewById(R.id.eventName))
						.setText(event.name);
				((TextView) convertView.findViewById(R.id.eventFromTo))
						.setText(event.from + " - " + event.to);
				// return super.getView(position, convertView, parent);
				return convertView;
			}
		};

		// this, R.layout.event_list_item, stage.events) {
		// @Override
		// public View getView(int position, View convertView,
		// ViewGroup parent) {
		// Log.e("getView", "pos "+position+" cv "+convertView+" pra "+parent);
		// inflater.inflate(R.layout.event_list_item, null);
		// // return super.getView(position, convertView, parent);
		// return convertView;
		// }
		// }; // Specify the row template to use

		// Bind to our new adapter.
		setListAdapter(adapter);

	}

	private static final int ADD_NOTIFICATION = 1;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		StageEvent event = list.get(((AdapterContextMenuInfo)menuInfo).position);
		menu.add(Menu.NONE, ADD_NOTIFICATION, 0, "Pridat upozorneni na "+event).setIcon(android.R.drawable.ic_dialog_alert);
		// menu.add(Menu.NONE, PREFS_MENU, 1,
		// R.string.preferences).setIcon(android.R.drawable.ic_menu_preferences);
		// super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (ADD_NOTIFICATION==item.getItemId()) {			
			StageEvent event = list.get(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			Toast.makeText(this, "Upozorneni na "+event, 5000).show();
		}
		return true;
	}

	public abstract class StageAdapter extends BaseAdapter {

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return list.get(position);
		}

		public long getItemId(int position) {
			return position;// list.get(position).getId();
		}

		public abstract View getView(int position, View convertView,
				ViewGroup parent);

	}

}
