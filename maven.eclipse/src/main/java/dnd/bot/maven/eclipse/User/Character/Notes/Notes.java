package dnd.bot.maven.eclipse.User.Character.Notes;

import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;

public class Notes extends State {
    public List<BasicDescription> notes;

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        for (var note : notes) {
            var noteMessageObject = new MessageObject(note.name, note.description);
            response.addMessageObject(noteMessageObject);
        }

        return response;
    }   
}
