package cz.hackathon.programy.provider;

import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import cz.hackathon.programy.DomParser;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;

public class XmlActionProvider implements ActionProvider {
	
	//public static final String ODB_NAME = "festivaly_datastore";
	
	public ODB odb;
	
	public List<Action> getAvailableActions() {
        IQuery query = new CriteriaQuery(Action.class);
		Objects<Action> actions = odb.getObjects(query);
		return (List<Action>) actions;
	}

	public Action getAction(int actionId) {
        IQuery query = new CriteriaQuery(Action.class);
        Objects<Action> actions = odb.getObjects(query);
        int i = 0;
        for (Action a: actions) {
            if (i == actionId) {
                return a;
            }
            i++;
        }
        return null;
	}

	public Stage getStages(int actionId, int stageId) {
        Action a = getAction(actionId);
        return a.stages.get(stageId);
	}

	public void addXml(String path) {
		DomParser parser = new DomParser(path);
		List<Action> actions = parser.parse();
		
		List<Action> available = getAvailableActions();
		
		for (Action action : actions) {
			boolean found = false;
			for (Action availableAction : available) {
				if (!availableAction.name.equals(action.name) || !availableAction.id.equals(action.id)) {
					found = true;
					break;
				}
			}
			if (!found) {
				odb.store(action);
			}
		}
	}

}
