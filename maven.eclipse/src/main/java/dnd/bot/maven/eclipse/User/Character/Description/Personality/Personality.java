package dnd.bot.maven.eclipse.User.Character.Description.Personality;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance.Appearance;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.HP.HP;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Social.Social;

public class Personality extends State {
    private personalityRepository personalityPRepository;
    public HP hp;
    public Level level;
    public Appearance appearance;
    public Social social;

    public Personality() {
        possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToHP", hp);
            possibleTransitions.put("moveToLevel", level);
            possibleTransitions.put("moveToAppearance", appearance);
            possibleTransitions.put("moveToSocial", social);
        }};
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
        var hpInlineKeybordButton = getInlineKeybordButton("ХП", "moveToHP");
        possessionBonusMessageObject.addInlineKeybordButton(hpInlineKeybordButton);
        response.addMessageObject(possessionBonusMessageObject);

        var exhaustionMessageObject = new MessageObject("Степень истощения", String.format("%d", exhaustion));
        response.addMessageObject(exhaustionMessageObject);

        var inspirationMessageObject = new MessageObject("Вдохновение", (inspiration) ? "+" : "-");
        var levelInlineKeybordButton = getInlineKeybordButton("Уровень", "moveToLevel");
        var appearanceInlineKeybordButton = getInlineKeybordButton("Внешность", "moveToAppearance");
        var socialInlineKeybordButton = getInlineKeybordButton("Общественность", "moveToSocial");
        inspirationMessageObject.addInlineKeybordButton(levelInlineKeybordButton);
        inspirationMessageObject.addInlineKeybordButton(appearanceInlineKeybordButton);
        inspirationMessageObject.addInlineKeybordButton(socialInlineKeybordButton);
        response.addMessageObject(inspirationMessageObject);

        return response;
    }
}
