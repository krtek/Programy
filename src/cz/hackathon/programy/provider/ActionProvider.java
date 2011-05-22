package cz.hackathon.programy.provider;

import cz.hackathon.programy.dto.Action;

import java.util.List;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 12:25
 */
public interface ActionProvider {
    /**
     * Returns list of actions stored in device.
     * @return
     */
    public List<Action> getAvailableActions();
}
