package cz.hackathon.programy;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;
import cz.hackathon.programy.dto.StageEvent;


public class DomParser extends BaseFeedParser {
	
	final static String ACTIONS = "actions";
	final static String ACTION = "action";
	final static String ID = "id";
	final static String NAME = "name";
	final static String DESCRIPTION = "description";
	final static String URL = "url";
	final static String IMAGE = "image";
	final static String LAT = "lat";
	final static String LON = "lon";
	final static String DATE = "date";
	final static String STAGE = "stage";
	final static String STAGES = "stages";
	final static String EVENT = "event";
	final static String EVENTS = "events";
	final static String FROM = "FROM";
	final static String TO = "to";
	

    public DomParser(String feedUrl) {
        super(feedUrl);
    }

    public List<Action> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Action> actions = new ArrayList<Action>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            org.w3c.dom.Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName(ACTION);
            for (int i=0;i<items.getLength();i++){
                Action action = new Action();
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();
                for (int j=0;j<properties.getLength();j++){
                    Node property = properties.item(j);
                    String name = property.getNodeName();
                    if (name.equalsIgnoreCase(ID) && property.getFirstChild() != null){
                    	action.id = (property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(NAME) && property.getFirstChild() != null){
                    	action.name = (property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(DESCRIPTION) && property.getFirstChild() != null){
                        action.description = property.getFirstChild().getNodeValue();
                    } else if (name.equalsIgnoreCase(IMAGE) && property.getFirstChild() != null){
                        action.imageUrl = property.getFirstChild().getNodeValue();
                    } else if (name.equalsIgnoreCase(LAT) && property.getFirstChild() != null){
                        action.locationLat = property.getFirstChild().getNodeValue();
                    } else if (name.equalsIgnoreCase(LON) && property.getFirstChild() != null){
                        action.locationLong = property.getFirstChild().getNodeValue();
                    } else if (name.equalsIgnoreCase(STAGES) && property.getFirstChild() != null){
                    	action.stages = new ArrayList<Stage> ();
                        NodeList stagesNodes = ((Element) property).getElementsByTagName(STAGE);
                        for (int k=0;k<stagesNodes.getLength();k++){
                        	
                            Stage stage = new Stage();
                            Node stageItem = stagesNodes.item(k);
                            NodeList stageProperties = stageItem.getChildNodes();
                            for (int l=0;l<stageProperties.getLength();l++){
                                Node stageProperty = stageProperties.item(l);
                                String stageName = stageProperty.getNodeName();
                                if (stageName.equalsIgnoreCase(NAME) && stageProperty.getFirstChild() != null){
                                	stage.name = stageProperty.getFirstChild().getNodeValue();
                                } else if (stageName.equalsIgnoreCase(DESCRIPTION) && stageProperty.getFirstChild() != null){
                                	stage.desc = stageProperty.getFirstChild().getNodeValue();
                                } else if (stageName.equalsIgnoreCase(EVENTS) && stageProperty.getFirstChild() != null){
                                	//stage.events = new ArrayList<StageEvent> ();
                                    NodeList eventsNodes = ((Element) stageProperty).getElementsByTagName(EVENT);
                                    for (int m=0;m<eventsNodes.getLength();m++){
                                    	
                                        StageEvent event = new StageEvent();
                                        Node eventItem = eventsNodes.item(m);
                                        NodeList eventProperties = eventItem.getChildNodes();
                                        for (int n=0;n<eventProperties.getLength();n++){
                                            Node eventProperty = eventProperties.item(n);
                                            String eventName = eventProperty.getNodeName();
                                            if (eventName.equalsIgnoreCase(NAME) && eventProperty.getFirstChild() != null){
                                            	Log.d("FEST", stage.name);
                                            	event.name = eventProperty.getFirstChild().getNodeValue();
                                               	Log.d("FEST", event.name);
                                            } else if (eventName.equalsIgnoreCase(FROM) && eventProperty.getFirstChild() != null){
                                            	event.from = eventProperty.getFirstChild().getNodeValue();
                                            } else if (eventName.equalsIgnoreCase(TO) && eventProperty.getFirstChild() != null){
                                            	event.to = eventProperty.getFirstChild().getNodeValue();
                                            }
                                        }
                                        stage.events.add(event);
                                  }
                                }
                            }
                            action.stages.add(stage);
                        }

                    	
                    }
                }
                actions.add(action);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
        return actions;
    }
}
