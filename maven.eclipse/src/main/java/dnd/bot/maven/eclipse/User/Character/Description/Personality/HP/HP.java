package dnd.bot.maven.eclipse.User.Character.Description.Personality.HP;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class HP extends State {
    //private HPRepository hpRepository;
    public int maxHP;
    public int bonusMaxHP;
    public String hpDice;
    public int tempHP;

    public HP() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
        // var data = hpRepository.getData();

        // var maxHP = data.maxHP;
        // var bonusMaxHP = data.bonusMaxHP;
        // var hpDice = data.hpDice;
        // var tempHP = data.tempHP;

		var response = new ResponseObject();

		var maxHPMessageObject = new MessageObject("Максимальное ХП", String.format("%d", maxHP));
        response.addMessageObject(maxHPMessageObject);

        var bonusMaxHPMessageObject = new MessageObject("Бонус к максимальному ХП", String.format("%d", bonusMaxHP));
        response.addMessageObject(bonusMaxHPMessageObject);

        var hpDiceMessageObject = new MessageObject("Кость хитов", hpDice);
        response.addMessageObject(hpDiceMessageObject);

        var tempHPMessageObject = new MessageObject("Временные хиты", String.format("%d", tempHP));
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		tempHPMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(tempHPMessageObject);

        return response;
	}
}
