package dnd.bot.maven.eclipse.Routing;
import java.util.HashMap;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import dnd.bot.maven.eclipse.Response.ResponseObject;

public abstract class State {
    public HashMap<String, State> possibleTransitions;

    public abstract ResponseObject getStateMessages();

    protected InlineKeyboardButton getInlineKeybordButton(String name, String callbackData) {
        var inlineKeybordButton = new InlineKeyboardButton();
        inlineKeybordButton.setText(String.format("%s", name));
        inlineKeybordButton.setCallbackData(callbackData);

        return inlineKeybordButton;
    }
}
