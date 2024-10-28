package dnd.bot.maven.eclipse.Response;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class MessageObject {
    private StringBuilder message;
    private List<InlineKeyboardButton> inlineButtons;

    public MessageObject() {
        message = new StringBuilder();
        inlineButtons = new ArrayList<InlineKeyboardButton>();
    }

    public MessageObject(String name, String messagePart) {
        message = new StringBuilder(name + " " + messagePart + "\n");
        inlineButtons = new ArrayList<InlineKeyboardButton>();
    }

    public MessageObject(List<String> names, List<String> messageData) {
        message = new StringBuilder();

        for (var i = 0; i < names.size(); i++)
            message.append(names.get(i) + " " + messageData.get(i) + "\n");
        
        inlineButtons = new ArrayList<InlineKeyboardButton>();
    }

    public void addMessagePart(String name, String messagePart) {
        message.append(name + " " + messagePart + "\n");
    }

    public String getMessage() {
        return message.toString();
    }

    public void addInlineKeybordButton(InlineKeyboardButton inlineKeyboardButton) {
        inlineButtons.add(inlineKeyboardButton);
    }

    public InlineKeyboardButton[] getInlineKeyboardButtons() {
        return (InlineKeyboardButton[])inlineButtons.toArray();
    }
}