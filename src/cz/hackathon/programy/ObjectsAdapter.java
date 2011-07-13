package cz.hackathon.programy;

import java.util.List;

import org.neodatis.odb.Objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

@SuppressWarnings("rawtypes")
public class ObjectsAdapter extends BaseAdapter {

	protected Objects objects;
	
	protected Context ctx;

	public ObjectsAdapter(Objects objects, Context ctx) {
		this.objects = objects;
		this.ctx = ctx;
	}

	public int getCount() {
		return this.objects.size();
	}

	public Object getItem(int i) {
		return ((List) this.objects).get(i);
	}

	public long getItemId(int i) {
		return ((List) this.objects).get(i).hashCode();
	}

	public View getView(int i, View convertView, ViewGroup viewGroup) {
		View v;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.action_row, null);
		} else {
			v = convertView;
		}

		return v;
	}
	
	public Objects getObjects() {
		return objects;
	}

}
