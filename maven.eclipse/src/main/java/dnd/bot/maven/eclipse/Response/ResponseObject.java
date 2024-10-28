package dnd.bot.maven.eclipse.Response;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
    private List<MessageObject> messages;

    public ResponseObject() {
        messages = new ArrayList<MessageObject>();
    }

    public ResponseObject(MessageObject messageObject) {
        messages = new ArrayList<MessageObject>();
        messages.add(messageObject);
    }

    public ResponseObject(List<MessageObject> messageObjects) {
        messages = new ArrayList<MessageObject>();
        for (var messageObject : messageObjects)
            messages.add(messageObject);
    }

    public void addMessageObject(MessageObject messageObject) {
        messages.add(messageObject);
    }

    public MessageObject[] getMessageObjects() {
        return (MessageObject[])messages.toArray();
    }
}