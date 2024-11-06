package dnd.bot.maven.eclipse.Response;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class MessageObject {
    private StringBuilder message = new StringBuilder();;
    private List<InlineKeyboardButton> inlineButtons  = new ArrayList<InlineKeyboardButton>();

    public MessageObject() {}
    
    public MessageObject(String name) {
        message.append(name);
    }

    public MessageObject(String name, String messagePart) {
        message.append(name + ": " + messagePart);
    }

    public MessageObject(List<String> names, List<String> messageData) {
        for (var i = 0; i < names.size(); i++)
            message.append(names.get(i) + ": " + messageData.get(i));
    }

    public void addMessagePart(String name, String messagePart) {
        message.append(name + ": " + messagePart);
    }

    public String getMessage() {
        return message.toString();
    }

    public void addInlineKeybordButton(InlineKeyboardButton inlineKeyboardButton) {
        inlineButtons.add(inlineKeyboardButton);
    }

    public List<InlineKeyboardButton> getInlineKeyboardButtons() {
        return inlineButtons;
    }
}