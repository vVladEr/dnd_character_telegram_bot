package dnd.bot.maven.eclipse.Routing.States;

import java.util.ArrayList;
import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class AddState extends BaseState {
    private HashMap<String, String> receivedFieldsData;
    private ArrayList<String> necessaryFields;
    private HashMap<String, String> addFieldsDescription;
    private Integer currentNumber;
    private CombineKey combineKey;
    
    public AddState(
        ArrayList<String> necessaryFields,
        CombineKey combinekey
    ) {
        receivedFieldsData = new HashMap<>();
        this.combineKey = combinekey;
        //this.addFieldsDescription = addFieldsDescription;
        this.necessaryFields = necessaryFields;
        this.currentNumber = 0;
    }

    public void putValue(String value) {
        receivedFieldsData.put(necessaryFields.get(currentNumber), value);
    }

    public boolean tryMoveNext() {
        if (currentNumber < necessaryFields.size() - 1) {
            currentNumber++;
            return true;
        }

        return false;
    }

    public CombineKey getSavedData() {
        return new CombineKey(combineKey.getUserIdKey(), combineKey.getObjectIdKey(), combineKey.getStateName(), combineKey.getGradeCompositeKey(), receivedFieldsData);
    }

    @Override
    public ResponseObject getStateMessages() {
        var responseObject = new ResponseObject();

        var messageObject = new MessageObject();

        messageObject.addMessagePart(String.format("%s", necessaryFields.get(currentNumber)));
       
        responseObject.addMessageObject(messageObject);

        return responseObject;
    }
}
