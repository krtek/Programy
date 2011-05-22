package cz.hackathon.programy;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	

    protected DomParser(String feedUrl) {
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
                    if (name.equalsIgnoreCase(ID)){
                    	action.id = (property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(NAME)){
                    	action.name = (property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(DESCRIPTION)){
                        action.description = property.getFirstChild().getNodeValue();
                    } else if (name.equalsIgnoreCase(IMAGE)){
                        action.imageUrl = property.getFirstChild().getNodeValue();
                    } else if (name.equalsIgnoreCase(STAGES)){
                    	List<Stage> stages = new ArrayList<Stage> ();
                    	NodeList stagesNodes = item.getChildNodes();
                        for (int k=0;i<items.getLength();k++){
                        	
                            Stage stage = new Stage();
                            Node stageItem = items.item(k);
                            NodeList stageProperties = item.getChildNodes();
                            for (int l=0;j<properties.getLength();l++){
                                Node stageProperty = properties.item(l);
                                String stageName = property.getNodeName();
                                if (name.equalsIgnoreCase(NAME)){
                                	stage.name = (property.getFirstChild().getNodeValue());
                                } else if (name.equalsIgnoreCase(DESCRIPTION)){
                                	stage.desc = property.getFirstChild().getNodeValue();
                                } else if (name.equalsIgnoreCase(EVENTS)){
                                	List<StageEvent> stageEvents = new ArrayList<StageEvent> ();
                                	NodeList stageEventNodes = item.getChildNodes();
                                }
                            }
                            actions.add(action);
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
