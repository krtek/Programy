package cz.hackathon.programy.db;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OdbConfiguration;

import android.content.Context;
import android.util.Log;

public class OdbProvider {
	
	private ODB odb;
	private String fileName;
	
	
	public OdbProvider (Context context, String dbName) {
		try {
			OdbConfiguration.setDatabaseCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("FEST", "konf", e);
		}
		
        File directory = context.getDir("data", Context.MODE_PRIVATE);
        fileName = directory.getAbsolutePath()+"/" +dbName;
	}
	
	public void open () {
        odb = ODBFactory.open(fileName);
	}
	
	public void close () {
        odb.close();
	}
	
	public ODB getOdb() {
		return odb;
	}

	public String getFileName() {
		return fileName;
	}


}
