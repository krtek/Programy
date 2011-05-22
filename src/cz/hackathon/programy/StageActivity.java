package cz.hackathon.programy;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import cz.hackathon.programy.dto.Stage;
import cz.hackathon.programy.dto.StageEvent;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

public class StageActivity extends ListActivity {
	
	
	List<StageEvent> list;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		Intent i = getIntent();
		i.getExtras().getInt(ActionProvider.ACTION_ID);

		Stage stage = ProviderFactory.getProvider().getStages(
				i.getExtras().getInt(ActionProvider.ACTION_ID),
				i.getExtras().getInt(ActionProvider.STAGE_ID));

		this.list = stage.events;		
		
		final LayoutInflater inflater = getLayoutInflater();

		ListAdapter adapter = new StageAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				 Log.e("getView", "pos "+position+" cv "+convertView+" pra "+parent);
				 convertView = inflater.inflate(R.layout.event_list_item, null);
				 StageEvent event = list.get(position);
				 ((TextView)convertView.findViewById(R.id.eventName)).setText(event.name);
				 ((TextView)convertView.findViewById(R.id.eventFromTo)).setText(
						 event.from+" - "+event.to);
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

	public abstract class StageAdapter extends BaseAdapter {
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;//list.get(position).getId();
		}

		public abstract View getView(int position, View convertView,
				ViewGroup parent);

	}

}
