package dnd.bot.maven.eclipse.Response;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
    private List<MessageObject> messages = new ArrayList<MessageObject>();

    public ResponseObject() {
    }

    public ResponseObject(MessageObject messageObject) {
        messages.add(messageObject);
    }

    public ResponseObject(List<MessageObject> messageObjects) {
        for (var messageObject : messageObjects)
            messages.add(messageObject);
    }

    public void addMessageObject(MessageObject messageObject) {
        messages.add(messageObject);
    }

    public List<MessageObject> getMessageObjects() {
        return messages;
    }
}