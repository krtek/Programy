package cz.hackathon.programy.provider;

import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import cz.hackathon.programy.DomParser;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;

public class XmlActionProvider implements ActionProvider {
	
	//public static final String ODB_NAME = "festivaly_datastore";
	
	public ODB odb;
	
	@Override
	public List<Action> getAvailableActions() {
        IQuery query = new CriteriaQuery(Action.class);
		Objects<Action> actions = odb.getObjects(query);
		return (List<Action>) actions;
	}

	@Override
	public Action getAction(int actionId) {
        return null;
	}

	@Override
	public Stage getStages(int actionId, int stageId) {
        return null;
	}

	@Override
	public void addXml(String path) {
		DomParser parser = new DomParser(path);
		List<Action> actions = parser.parse();
		
		for (Action action : actions) {
			odb.store(action);
		}
	}

}
