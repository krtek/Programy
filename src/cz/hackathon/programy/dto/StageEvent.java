package cz.hackathon.programy.dto;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 11:49
 */
public class StageEvent {
    public String name;
    public String from;
    public String to;

    @Override
    public String toString() {
        return "StageEvent{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
