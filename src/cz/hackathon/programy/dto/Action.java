package cz.hackathon.programy.dto;

import java.util.List;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 11:47
 */
public class Action {
    public String id;
    public String name;
    public String description;
    public String imageUrl;
    public String webUrl;
    public String locationLat;
    public String locationLong;

    public List<Stages> stages;

    @Override
    public String toString() {
        return "Action{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", locationLat='" + locationLat + '\'' +
                ", locationLong='" + locationLong + '\'' +
                ", stages=" + stages +
                '}';
    }
}
