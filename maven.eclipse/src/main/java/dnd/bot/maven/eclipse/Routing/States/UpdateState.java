package dnd.bot.maven.eclipse.Routing.States;

import java.util.ArrayList;
import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.db.Models.UpdateFieldRequest;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Services.UpdateManager;

public class UpdateState extends BaseState {
    private Combinekey combineKey;
    private UpdateManager updateManager;
    private HashMap<String, String> updatedFields;
    private HashMap<String, String> updatedFieldsDescription;
    private ArrayList<String> fields;
    private String currentUpdatedField;

    public UpdateState( 
        ArrayList<String> fields,
        Combinekey combinekey,
        UpdateManager updateManager
    ) {
        updatedFields = new HashMap<String, String>();
        this.combineKey = combinekey;
        this.updateManager = updateManager;
        //this.updatedFieldsDescription = updatedFieldsDescription;
        this.fields = fields;
        this.currentUpdatedField = null;
        stateName = combineKey.getStateName();
        possibleTransitions = new HashMap<String, Combinekey>();
        possibleTransitions.put(String.format("goto%s", stateName.toLowerCase()), combinekey);
    }

    public void putUpdatedField(String updatedField) {
        currentUpdatedField = updatedField;
    }

    public String getUpdatedField() {
        return currentUpdatedField;
    }

    public void putValue(String value) {
        updatedFields.put(currentUpdatedField, value);
        this.currentUpdatedField = null;
    }

    public void saveUpdates() {
        for (var updatedField : updatedFields.keySet()) {
            var updateFieldRequest = new UpdateFieldRequest(combineKey, updatedField, updatedFields.get(updatedField), stateName);
            this.updateManager.updateField(updateFieldRequest);
        }
    }

    @Override
    public ResponseObject getStateMessages() {
        var responseObject = new ResponseObject();

        var messageObject = new MessageObject();

        if (currentUpdatedField == null) {
            messageObject.addMessagePart("Выберите поле для изменения");

            for (var field : fields) {
                messageObject.addInlineKeybordButton(getInlineKeybordButton(field, field));
            }

            messageObject.addInlineKeybordButton(getInlineKeybordButton("Сохранить", String.format("save:goto%s", stateName.toLowerCase())));

        } else {
            messageObject.addMessagePart(String.format("%s", currentUpdatedField), currentUpdatedField);
        }
        
        messageObject.addInlineKeybordButton(getInlineKeybordButton("Отмена", String.format("goto%s", combineKey.getStateName().toLowerCase())));

        responseObject.addMessageObject(messageObject);

        return responseObject;
    }
}
