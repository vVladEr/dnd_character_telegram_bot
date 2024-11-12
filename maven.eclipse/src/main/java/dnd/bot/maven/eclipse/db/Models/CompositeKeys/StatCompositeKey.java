package dnd.bot.maven.eclipse.db.Models.CompositeKeys;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;

public final class StatCompositeKey {

    public final ObjectId characterId;
    
    public final StatNameEnum statName;

    public StatCompositeKey(ObjectId characterId, StatNameEnum statName)
    {
        this.characterId = characterId;
        this.statName = statName;
    }
}
