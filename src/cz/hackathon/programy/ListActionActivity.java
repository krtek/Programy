package cz.hackathon.programy;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.StageEvent;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

/**
 * User: lukas.marek@cleverlance.com Date: 22.5.11 Time: 14:05
 */
public class ListActionActivity extends Activity {

	protected ActionProvider provider = null;
	protected ActionAdapter  adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actions);
	}

	@Override
	protected void onResume() {
		super.onResume();

		ListView list = (ListView) findViewById(R.id.list);
		registerForContextMenu(list);

		provider = ProviderFactory.getProvider(this);

		adapter = new ActionAdapter(provider.getAvailableActions(), this); 
		
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setClass(getBaseContext(), FestTabActivity.class);
				intent.putExtra(ActionProvider.ACTION_ID, i);
				startActivity(intent);
			}
		});
	}

	private static final int DEL_ACTION = 1;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (provider != null) {
			Action action = provider
					.getAction(((AdapterContextMenuInfo) menuInfo).position);
			if (action != null) {
				menu.add(Menu.NONE, DEL_ACTION, 0,
						"Odstranit " + action.name).setIcon(
						android.R.drawable.ic_input_delete);
			}
		}
		// menu.add(Menu.NONE, PREFS_MENU, 1,
		// R.string.preferences).setIcon(android.R.drawable.ic_menu_preferences);
		// super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (DEL_ACTION == item.getItemId()) {
			int index = ((AdapterContextMenuInfo) item.getMenuInfo()).position;
			provider.removeAction(index);
			adapter.remove(index);	
            adapter.notifyDataSetChanged();
		}
		return true;
	}
}

class ActionAdapter extends BaseAdapter {
	private List<Action> actions;
	private Context ctx;

	public ActionAdapter(List<Action> actions, Context ctx) {
		this.actions = actions;
		this.ctx = ctx;
	}

	public int getCount() {
		return this.actions.size();
	}

	public Object getItem(int i) {
		return this.actions.get(i);
	}

	public long getItemId(int i) {
		return this.actions.get(i).id.hashCode();
	}

	public View getView(int i, View convertView, ViewGroup viewGroup) {
		Action a = this.actions.get(i);
		View v;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.action_row, null);
		} else {
			v = convertView;
		}

		((TextView) v.findViewById(R.id.action_name)).setText(a.name);
		return v;
	}
	
	public void remove (int i) {
		this.actions.remove(i);
	}
}
