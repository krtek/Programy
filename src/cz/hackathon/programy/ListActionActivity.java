package cz.hackathon.programy;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.provider.ActionProvider;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 14:05
 */
public class ListActionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actions);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView list = (ListView) findViewById(R.id.list);
		
		ActionProvider provider = ((FestivalyApplication)getApplication()).provider;
		

        list.setAdapter(new ActionAdapter(provider.getAvailableActions(), this));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(getBaseContext(), FestTabActivity.class);
                intent.putExtra(ActionProvider.ACTION_ID, i);
                startActivity(intent);
            }
        });
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
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.action_row, null);
        } else {
            v = convertView;
        }

        ((TextView)v.findViewById(R.id.action_name)).setText(a.name);
        return v;
    }
}
