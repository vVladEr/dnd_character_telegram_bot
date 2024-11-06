package dnd.bot.maven.eclipse.User.Character.Inventory.Money;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Money extends State {
    public int money;

    public Money() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        var platinumCount = money / 1000;
        money -= platinumCount * 1000;
        var goldCount = money / 100;
        money -= goldCount * 100;
        var electrumCount = money / 50;
        money -= electrumCount * 50;
        var silverCount = money / 10;
        money -= silverCount * 10;
        var copperCount = money;

        if (platinumCount > 0) {
            var platinumCountMessageObject = new MessageObject("Платиновых монет", String.format("%d", platinumCount));
            response.addMessageObject(platinumCountMessageObject);
        }

        if (goldCount > 0) {
            var goldCountMessageObject = new MessageObject("Золотых монет", String.format("%d", goldCount));
            response.addMessageObject(goldCountMessageObject);
        }

        if (electrumCount > 0) {
            var electrumCountMessageObject = new MessageObject("Электрумовых монет", String.format("%d", electrumCount));
            response.addMessageObject(electrumCountMessageObject);
        }    

        if (silverCount > 0) {
            var silverCountMessageObject = new MessageObject("Серебряных монет", String.format("%d", silverCount));
            response.addMessageObject(silverCountMessageObject);
        }

        var copperCountMessageObject = new MessageObject("Медных монет", String.format("%d", copperCount));
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		copperCountMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(copperCountMessageObject);

        return response;
    }
    
}
