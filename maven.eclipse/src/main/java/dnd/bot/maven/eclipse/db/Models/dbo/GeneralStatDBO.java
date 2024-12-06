package dnd.bot.maven.eclipse.db.Models.dbo;

import java.util.LinkedHashMap;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;
import dnd.bot.maven.eclipse.db.Services.SkillFactory;

public class GeneralStatDBO {

    @BsonProperty
    public ObjectId characterId;

    @BsonProperty
    public StatNameEnum statName;

    @BsonProperty
    public int value;

    @BsonProperty
    public LinkedHashMap<String, SkillDbo> skills;

    @BsonProperty
    public int checkValue;

    @BsonProperty
    public int saveThrowValue;

    @BsonProperty
    public boolean isMastered;

    public GeneralStatDBO() {
    }

    public GeneralStatDBO(ObjectId characterId, StatNameEnum statName, LinkedHashMap<String, SkillDbo> skills) {
        this.characterId = characterId;
        this.statName = statName;
        this.skills = skills;
        value = 10;
    }

    public GeneralStatDBO(ObjectId characterId, StatNameEnum statName) {
        this(characterId, statName, SkillFactory.getSkillsByStatName(statName));
    }

}
