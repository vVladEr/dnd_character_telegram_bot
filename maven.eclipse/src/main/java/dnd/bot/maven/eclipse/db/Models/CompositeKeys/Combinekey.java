package dnd.bot.maven.eclipse.db.Models.CompositeKeys;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;

public class Combinekey {

    private final GradeCompositeKey gradeCompositeKey;

    private final StatCompositeKey statCompositeKey;

    private final ObjectId objectIdKey;

    public Combinekey(ObjectId characterId, int gradeNumber)
    {
        gradeCompositeKey = new GradeCompositeKey(characterId, gradeNumber);
        statCompositeKey = null;
        objectIdKey = null;
    }
    
    public Combinekey(ObjectId characterId, StatNameEnum statName)
    {
        gradeCompositeKey = null;
        statCompositeKey = new StatCompositeKey(characterId, statName);
        objectIdKey = null;
    }

    public Combinekey(ObjectId objectId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = objectId;
    }

    public GradeCompositeKey getGradeCompositeKey() {
        assert gradeCompositeKey != null;

        return gradeCompositeKey;
    }

    public StatCompositeKey getStatCompositeKey() {
        assert statCompositeKey != null;

        return statCompositeKey;
    }

    public ObjectId getObjectIdKey() {
        assert objectIdKey != null;

        return objectIdKey;
    }
}
