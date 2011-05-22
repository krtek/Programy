package cz.hackathon.programy.provider;

import java.io.File;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import android.content.Context;
import cz.hackathon.programy.DomParser;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;

public class XmlActionProvider implements ActionProvider {
	
	public static final String ODB_NAME = "festivaly_datastore";
	
	public ODB odb;
	
	@Override
	public List<Action> getAvailableActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action getAction(int actionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stage getStages(int actionId, int stageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addXml(String path) {
		DomParser parser = new DomParser(path);
		List<Action> actions = parser.parse();
		
        try {

            for (Action action : actions) {
            	odb.store(action);
            }
        } finally {
            if (odb != null) {
                // Close the database
                odb.close();
            }
        }

	}

}
