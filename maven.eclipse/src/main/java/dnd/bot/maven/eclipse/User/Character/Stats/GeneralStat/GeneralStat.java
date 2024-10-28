package dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Skill;

import java.util.List;

public abstract class GeneralStat extends State {
	public String name;
	public int value;
	public List<Skill> skills;
	public int checkBonus;
	public int saveThrowBonus;

	@Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

		var nameMessageObject = new MessageObject("", name);
        response.addMessageObject(nameMessageObject);

		var valueMessageObject = new MessageObject("Значение статы", String.format("%d", value));
        response.addMessageObject(valueMessageObject);

		var checkBonusMessageObject = new MessageObject("Бонус проверки", String.format("%d", checkBonus));
        response.addMessageObject(checkBonusMessageObject);

		var saveThrowBonusMessageObject = new MessageObject("Бонус спасброска", String.format("%d", saveThrowBonus));
        response.addMessageObject(saveThrowBonusMessageObject);

		var skillMessageObject = new MessageObject("Выберите навык", "");

		for (var i = 0; i < skills.size(); i++) {
			skillMessageObject.addInlineKeybordButton(getInlineKeybordButton(skills.get(i).name, skills.get(i).name));
		}

		response.addMessageObject(skillMessageObject);

		return response;
	}
}
