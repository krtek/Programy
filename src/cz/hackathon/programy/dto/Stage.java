package cz.hackathon.programy.dto;

import java.util.List;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 11:48
 */
public class Stage {
    public String name;
    public String desc;
    public List<StageEvent> events;

    @Override
    public String toString() {
        return "Stages{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", events=" + events +
                '}';
    }
}
