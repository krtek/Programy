package cz.hackathon.programy.provider;

import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import cz.hackathon.programy.DomParser;
import cz.hackathon.programy.db.OdbProvider;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;

public class XmlActionProvider implements ActionProvider {

	private List<Action> actions = null;

	public OdbProvider odbProvider;
	
	
	private List<Action> readActions () {
		IQuery query = new CriteriaQuery(Action.class);
		Objects<Action> actions = null;
		odbProvider.open();
		try {
			actions = odbProvider.getOdb().getObjects(query);
		} finally {
			odbProvider.close();
		}
		return (List<Action>) actions;
	}

	public List<Action> getAvailableActions() {
		if (actions == null) {
			actions = readActions();
		}
		return actions;
	}

	public Action getAction(int actionId) {
		if (actions == null) {
			actions = readActions();
		}
		
		return actions.get(actionId);
		
		/*
		// IQuery query = new CriteriaQuery(Action.class, Where.equal("id",
		// actionId));
		
		IQuery query = new CriteriaQuery(Action.class);
		Objects<Action> actions = null;
		odbProvider.open();
		try {
			actions = odbProvider.getOdb().getObjects(query);
			int i = 0;
			for (Action a : actions) {
				if (i == actionId) {
					return a;
				}
				i++;
			}
		} finally {
			odbProvider.close();
		}
		return null;
		*/
	}

	public Stage getStages(int actionId, int stageId) {
		Action a = getAction(actionId);
		return a.stages.get(stageId);
	}

	public void addXml(String path) {
		if (actions == null) {
			actions = readActions();
		}
		DomParser parser = new DomParser(path);
		List<Action> newActions = parser.parse();

		List<Action> available = getAvailableActions();

		odbProvider.open();
		try {
			for (Action action : newActions) {
				boolean found = false;
				for (Action availableAction : available) {
					if (availableAction.name.equals(action.name)
							&& availableAction.id.equals(action.id)) {
						found = true;
						break;
					}
				}
				if (!found) {
					odbProvider.getOdb().store(action);
					if (actions != null) actions.add(action);
				}
			}
		} finally {
			odbProvider.close();
		}
	}

}
