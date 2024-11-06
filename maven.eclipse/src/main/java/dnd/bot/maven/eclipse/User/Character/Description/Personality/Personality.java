package dnd.bot.maven.eclipse.User.Character.Description.Personality;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance.Appearance;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.HP.HP;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Social.Social;

public class Personality extends State {
    public int armor;
    public int speed;
    public int possessionBonus;
    public boolean inspiration;
    public int exhaustion;

    public Personality(
            HP hp, 
            Level level, 
            Appearance appearance,
            Social social
        ) {
        possibleTransitions = new HashMap<String, State>();
        possibleTransitions.put("moveToHP", hp);
        possibleTransitions.put("moveToLevel", level);
        possibleTransitions.put("moveToAppearance", appearance);
        possibleTransitions.put("moveToSocial", social);
        possibleTransitions.put("back", new Back());
    }

    @Override
	public ResponseObject getStateMessages() {
        var data = personalityRepository.getData();

        var armor = data.armor;
        var speed = data.speed;
        var possessionBonus = data.possessionBonus;
        var inspiration = data.inspiration;
        var exhaustion = data.exhaustion;

		var response = new ResponseObject();	

        var armorMessageObject = new MessageObject("КД", String.format("%d", armor));
        response.addMessageObject(armorMessageObject);

        var speedMessageObject = new MessageObject("Скорость", String.format("%d", speed));
        response.addMessageObject(speedMessageObject);

        var possessionBonusMessageObject = new MessageObject("Бонус владения", String.format("%d", possessionBonus));
        response.addMessageObject(possessionBonusMessageObject);

        var exhaustionMessageObject = new MessageObject("Степень истощения", String.format("%d", exhaustion));
        response.addMessageObject(exhaustionMessageObject);

        var inspirationMessageObject = new MessageObject("Вдохновение", (inspiration) ? "есть" : "нет");
        var levelInlineKeybordButton = getInlineKeybordButton("Уровень", "moveToLevel");
        var appearanceInlineKeybordButton = getInlineKeybordButton("Внешность", "moveToAppearance");
        var socialInlineKeybordButton = getInlineKeybordButton("Общественность", "moveToSocial");
        var hpInlineKeybordButton = getInlineKeybordButton("ХП", "moveToHP");
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
        inspirationMessageObject.addInlineKeybordButton(hpInlineKeybordButton);
        inspirationMessageObject.addInlineKeybordButton(levelInlineKeybordButton);
        inspirationMessageObject.addInlineKeybordButton(appearanceInlineKeybordButton);
        inspirationMessageObject.addInlineKeybordButton(socialInlineKeybordButton);
		inspirationMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(inspirationMessageObject);

        return response;
    }
}
