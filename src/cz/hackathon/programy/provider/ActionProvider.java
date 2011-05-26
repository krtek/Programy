package cz.hackathon.programy.provider;

import java.util.List;

import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 12:25
 */
public interface ActionProvider {
    public static final String ACTION_ID = "actionId";
    public static final String STAGE_ID = "stageId";
    /**
     * Returns list of actions stored in device.
     * @return
     */
    public List<Action> getAvailableActions();
    public Action getAction(int actionId);
    public Stage getStages(int actionId, int stageId);
    public void removeAction(int actionId);
    public void addXml(String path);
}
