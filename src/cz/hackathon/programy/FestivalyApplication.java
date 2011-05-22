package cz.hackathon.programy;

import java.io.File;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import android.app.Application;
import android.content.Context;
import cz.hackathon.programy.provider.XmlActionProvider;

public class FestivalyApplication extends Application {
	
	public ODB odb;
	
	public XmlActionProvider provider;

	@Override
	public void onCreate() {
        // Ask Android where we can store our file
        File directory = getDir("data", Context.MODE_PRIVATE);
        String fileName = directory.getAbsolutePath()+"/test-android.neodatis";

        // Opens the NeoDatis database
        odb = ODBFactory.open(fileName);
        
        provider = new XmlActionProvider();
        provider.odb = odb;
        
	}

}
