package dnd.bot.maven.eclipse.db.Models;

import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class UpdateFieldRequest {

    public final Combinekey key;

    public final String fieldName;

    public final String innerFieldName;

    public final Object fieldValue;

    public final String stateName;

    public UpdateFieldRequest(Combinekey key, String fieldName, String innerFieldName, Object fieldValue, String stateName)
    {
        this.key = key;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.stateName = stateName;
        this.innerFieldName = innerFieldName;
    }

    public UpdateFieldRequest(Combinekey key, String fieldName, Object fieldValue, String stateName)
    {
        this(key, fieldName, null, fieldValue, stateName);
    }
}
