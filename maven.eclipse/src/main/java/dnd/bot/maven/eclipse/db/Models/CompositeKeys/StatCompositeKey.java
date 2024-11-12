package dnd.bot.maven.eclipse.db.Models.CompositeKeys;

import org.bson.types.ObjectId;

public final class StatCompositeKey {

    public final ObjectId characterId;
    
    public final String statName;

    public StatCompositeKey(ObjectId characterId, String statName)
    {
        this.characterId = characterId;
        this.statName = statName;
    }
}
