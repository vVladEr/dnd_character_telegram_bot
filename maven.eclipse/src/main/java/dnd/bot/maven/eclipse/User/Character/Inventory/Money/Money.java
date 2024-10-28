package dnd.bot.maven.eclipse.User.Character.Inventory.Money;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;

public class Money extends State {
    public int money;

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        var silverCount = money % 10;
        var electrumCount = money % 50_0;
        var goldCount = money % 50_0_00;
        var platinumCount = money % 50_0_00_000;
        var copperCount = money - silverCount - electrumCount - goldCount - platinumCount;

        if (copperCount > 0) {
            var copperCountMessageObject = new MessageObject("Медных монет", String.format("%d", copperCount));
            response.addMessageObject(copperCountMessageObject);
        }
        
        if (electrumCount > 0) {
            var electrumCountMessageObject = new MessageObject("Электрумовых монет", String.format("%d", electrumCount));
            response.addMessageObject(electrumCountMessageObject);
        }

        if (silverCount > 0) {
            var silverCountMessageObject = new MessageObject("Серебряных монет", String.format("%d", silverCount));
            response.addMessageObject(silverCountMessageObject);
        }

        if (goldCount > 0) {
            var goldCountMessageObject = new MessageObject("Золотых монет", String.format("%d", goldCount));
            response.addMessageObject(goldCountMessageObject);
        }

        if (platinumCount > 0) {
            var platinumCountMessageObject = new MessageObject("Платиновых монет", String.format("%d", platinumCount));
            response.addMessageObject(platinumCountMessageObject);
        }

        return response;
    }
    
}
