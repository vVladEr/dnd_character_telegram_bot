package dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;

public class Appearance extends State {
    public String alignment;
    public int height;
    public int age;
    public int weight;
    public String backGround;
    public BasicDescription characterBackground;
    public BasicDescription allies;
    public BasicDescription personality;
    public BasicDescription ideals;
    public BasicDescription bonds;
    public BasicDescription flaws;

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();
    
        var alignmentMessageObject = new MessageObject("Мировоззрение", alignment);
        response.addMessageObject(alignmentMessageObject);

        var heightMessageObject = new MessageObject("Мировоззрение", String.format("%d", height));
        response.addMessageObject(heightMessageObject);

        var ageMessageObject = new MessageObject("Мировоззрение", String.format("%d", age));
        response.addMessageObject(ageMessageObject);

        var weightMessageObject = new MessageObject("Мировоззрение", String.format("%d", weight));
        response.addMessageObject(weightMessageObject);

        var backGroundMessageObject = new MessageObject("Предыстория", backGround);
        response.addMessageObject(backGroundMessageObject);

        var characterBackgroundMessageObject = new MessageObject(characterBackground.name, characterBackground.description);
        response.addMessageObject(characterBackgroundMessageObject);

        var alliesMessageObject = new MessageObject(allies.name, allies.description);
        response.addMessageObject(alliesMessageObject);

        var personalityMessageObject = new MessageObject(personality.name, personality.description);
        response.addMessageObject(personalityMessageObject);

        var idealsMessageObject = new MessageObject(ideals.name, ideals.description);
        response.addMessageObject(idealsMessageObject);

        var flawsMessageObject = new MessageObject(flaws.name, flaws.description);
        response.addMessageObject(flawsMessageObject);

        return response; 
	}
}
