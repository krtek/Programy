package cz.hackathon.programy;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import cz.hackathon.programy.dto.StageEvent;
import cz.hackathon.programy.dto.Stages;
import cz.hackathon.programy.provider.ActionProvider;
import cz.hackathon.programy.provider.ProviderFactory;

public class StageActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Intent i = getIntent();
		i.getExtras().getInt(ActionProvider.ACTION_ID);
		
		Stages stage = ProviderFactory.getProvider().getStages(
				i.getExtras().getInt(ActionProvider.ACTION_ID),
				i.getExtras().getInt(ActionProvider.STAGE_ID));

		ListAdapter adapter = new ArrayAdapter<StageEvent>(this, // Context.
				R.layout.event_list_item, stage.events) {
		}; // Specify the row template to use

		// Bind to our new adapter.
		setListAdapter(adapter);

	}
}
