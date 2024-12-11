package dnd.bot.maven.eclipse.Routing.States;

import java.util.ArrayList;
import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class AddState extends BaseState {
    private HashMap<String, String> receivedFieldsData;
    private ArrayList<String> necessaryFields;
    private HashMap<String, String> addFieldsDescription;
    private Integer currentNumber;
    private Combinekey combineKey;
    
    public AddState(
        ArrayList<String> necessaryFields,
        Combinekey combinekey
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

    public Combinekey getSavedData() {
        return new Combinekey(combineKey.getUserIdKey(), combineKey.getObjectIdKey(), combineKey.getStateName(), receivedFieldsData);
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
