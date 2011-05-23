package cz.hackathon.programy;

import android.app.Application;
import cz.hackathon.programy.db.OdbProvider;
import cz.hackathon.programy.provider.XmlActionProvider;

public class FestivalyApplication extends Application {
	
	OdbProvider odbProvider;
	public XmlActionProvider provider;

	@Override
	public void onCreate() {
        // Ask Android where we can store our file
		super.onCreate();

		odbProvider = new OdbProvider(this.getApplicationContext(), "fesitvaly.neodatis");
		provider = new XmlActionProvider();
        provider.odbProvider = odbProvider;
	}
	
	@Override
	public void onTerminate() {
		//
	}

}
