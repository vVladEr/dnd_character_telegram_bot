package dnd.bot.maven.eclipse.User.Character.Description.Features;

import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;

public class Features extends State {
    public List<BasicDescription> features;

    @Override
	public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        for (var feature : features) {
            var featureMessageObject = new MessageObject(feature.name, feature.description);
            response.addMessageObject(featureMessageObject);
        }

        return response;
	}
}