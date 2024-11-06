package dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Knowledge.KnowledgeLevel;

public class Skill extends State {
    private SkillRepository skillRepository;
    public String name;
    public KnowledgeLevel knowledgeLevel;
    public int totalBonus;

    public Skill() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
    public ResponseObject getStateMessages() {
        var data = skillRepository.getData();

        var name = data.name;
        var knowledgeLevel = data.knowledgeLevel;
        var totalBonus = data.totalBonus;

        var response = new ResponseObject();

        var nameMessageObject = new MessageObject(name);
        response.addMessageObject(nameMessageObject);

        var knowledgeLevelMessageObject = new MessageObject();
        switch (knowledgeLevel) {
            case BASIC:
                knowledgeLevelMessageObject.addMessagePart("Уровень владения", "не владеет");
                break;
            case INTERMEDIATE:
                knowledgeLevelMessageObject.addMessagePart("Уровень владения", "владеет");
                break;
            case ADVANCED:
                knowledgeLevelMessageObject.addMessagePart("Уровень владения", "компетенция");
                break;
            default:
                break;
        }
        response.addMessageObject(knowledgeLevelMessageObject);
        
        var totalBonusMessageObject = new MessageObject("Общий бонус", String.format("%d", totalBonus));
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		totalBonusMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(totalBonusMessageObject);
        
        return response;
    }
}
    
