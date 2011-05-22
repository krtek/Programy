package cz.hackathon.programy;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OdbConfiguration;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import cz.hackathon.programy.provider.XmlActionProvider;

public class FestivalyApplication extends Application {
	
	public ODB odb;
	
	public XmlActionProvider provider;

	@Override
	public void onCreate() {
        // Ask Android where we can store our file
		super.onCreate();

		try {
			OdbConfiguration.setDatabaseCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("FEST", "konf", e);
		}
		
        File directory = getDir("data", Context.MODE_PRIVATE);
        String fileName = directory.getAbsolutePath()+"/test-android.neodatis7";

        // Opens the NeoDatis database
        odb = ODBFactory.open(fileName);
        
        provider = new XmlActionProvider();
        provider.odb = odb;
        
	}
	
	@Override
	public void onTerminate() {
		odb.close();
	}

}
