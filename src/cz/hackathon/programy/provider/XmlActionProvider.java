package cz.hackathon.programy.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import cz.hackathon.programy.DomParser;
import cz.hackathon.programy.db.OdbProvider;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;
import cz.hackathon.programy.dto.StageEvent;
import cz.hackathon.programy.utils.HoursComparator;

public class XmlActionProvider implements ActionProvider {

	private List<Action> actions = null;

	public OdbProvider odbProvider;

	private List<Action> readActions() {
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
		 * // IQuery query = new CriteriaQuery(Action.class, Where.equal("id",
		 * // actionId));
		 * 
		 * IQuery query = new CriteriaQuery(Action.class); Objects<Action>
		 * actions = null; odbProvider.open(); try { actions =
		 * odbProvider.getOdb().getObjects(query); int i = 0; for (Action a :
		 * actions) { if (i == actionId) { return a; } i++; } } finally {
		 * odbProvider.close(); } return null;
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
					if (actions != null)
						actions.add(action);
				}
			}
		} finally {
			odbProvider.close();
		}
	}

	@Override
	public void removeAction(int actionId) {

		Action action = getAction(actionId);
		if (action != null) {
			odbProvider.open();
			try {
				IQuery query = new CriteriaQuery(Action.class, Where.equal(
						"name", action.name));
				Objects<Action> actions = odbProvider.getOdb()
						.getObjects(query);
				if (actions != null) {
					odbProvider.getOdb().delete(actions.getFirst());
				}
			} finally {
				odbProvider.close();
			}
		}
	}

	@Override
	public List<StageEvent> getFollowingEvents(int actionId) {
		List<StageEvent> followingEvents = new ArrayList<StageEvent>();
		Action action = getAction(actionId);
		if (action != null) {
			
			Date curDateTime = new Date();
			int actual_hour = curDateTime.getHours();
			
			for (Stage stage : action.stages) {
				for (StageEvent stageEvent : stage.events) {
					if (stageEvent.from != null) {
						String hour_str = stageEvent.from.substring(0, 2);
						int hour = Integer.parseInt(hour_str); 
						if (hour >= actual_hour) {
							followingEvents.add(stageEvent);
						}
					}
				}
			}
		}
		Collections.sort(followingEvents, new HoursComparator());
		return followingEvents;
	}

}
