package dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Appearance extends State {
    public String alignment;
    public int height;
    public int age;
    public int weight;
    public String backGround;
    public String characterBackground;
    public String allies;
    public String personality;
    public String ideals;
    public String bonds;
    public String flaws;

    public Appearance() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();
    
        var alignmentMessageObject = new MessageObject("Мировоззрение", alignment);
        response.addMessageObject(alignmentMessageObject);

        var heightMessageObject = new MessageObject("Рост", String.format("%d", height));
        response.addMessageObject(heightMessageObject);

        var ageMessageObject = new MessageObject("Возраст", String.format("%d", age));
        response.addMessageObject(ageMessageObject);

        var weightMessageObject = new MessageObject("Вес", String.format("%d фнт", weight));
        response.addMessageObject(weightMessageObject);

        var backGroundMessageObject = new MessageObject("Предыстория", backGround);
        response.addMessageObject(backGroundMessageObject);

        var characterBackgroundMessageObject = new MessageObject("Предыстория персонажа", characterBackground);
        response.addMessageObject(characterBackgroundMessageObject);

        var alliesMessageObject = new MessageObject("Организации", allies);
        response.addMessageObject(alliesMessageObject);

        var personalityMessageObject = new MessageObject("Черты характера", personality);
        response.addMessageObject(personalityMessageObject);

        var idealsMessageObject = new MessageObject("Идеалы", ideals);
        response.addMessageObject(idealsMessageObject);

        var bondsMessageObject = new MessageObject("Привязанности", bonds);
        response.addMessageObject(bondsMessageObject);

        var flawsMessageObject = new MessageObject("Слабости", flaws);
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		flawsMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(flawsMessageObject);

        return response; 
	}
}
