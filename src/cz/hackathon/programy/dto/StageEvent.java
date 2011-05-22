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

    public StageEvent(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public StageEvent() {

    }

    @Override
    public String toString() {
        return "StageEvent{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
