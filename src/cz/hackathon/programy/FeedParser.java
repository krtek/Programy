package cz.hackathon.programy;

import java.util.List;

import cz.hackathon.programy.dto.Action;

public interface FeedParser {
    List<Action> parse();
}
