package dnd.bot.maven.eclipse.User.Character.Description.Personality.Social;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;

public class Social extends State {
    private SocialRepository socialRepository;

    @Override
	public ResponseObject getStateMessages() {
        var data = socialRepository.getData();

        var race = data.race;
        var characterName = data.characterName;
        var characterClass = data.characterClass;

		var response = new ResponseObject();	

        var raceMessageObject = new MessageObject("Раса", String.format("%s", race));
        response.addMessageObject(raceMessageObject);

        var characterNameMessageObject = new MessageObject("Имя", String.format("%s", characterName));
        response.addMessageObject(characterNameMessageObject);

        var characterClassMessageObject = new MessageObject("Класс", String.format("%s", characterClass));
        response.addMessageObject(characterClassMessageObject);

        return response;
	}
}
