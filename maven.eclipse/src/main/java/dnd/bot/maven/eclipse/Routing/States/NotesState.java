package dnd.bot.maven.eclipse.Routing.States;

// import java.util.HashMap;

// import dnd.bot.maven.eclipse.Response.MessageObject;
// import dnd.bot.maven.eclipse.Response.ResponseObject;
// import dnd.bot.maven.eclipse.Routing.GeneratorManager;
// import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
// import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;

// public class NotesState extends BaseState {
//     private Combinekey parameters;

//     public NotesState(
//         Combinekey parameters,
//         HashMap<String, String> fields, 
//         HashMap<String, String> buttons, 
//         HashMap<String, Combinekey> possibleTransitions
//     ) {
//         this.fields = fields;
//         this.buttons = buttons;
//         this.possibleTransitions = possibleTransitions;
//     }

//     public void addNote(GeneratorManager manager) {
//         var repo = manager.getReposStorage().();
//         var description = new BasicDescriptionDbo(parameters.getObjectIdKey(), "", "");
//         repo.InsertDocument(description);
//     }

//     @Override
//     public ResponseObject getStateMessages() {
//         var responseObject = new ResponseObject();
        
//         var messageObject = new MessageObject();
//         for (var name : fields.keySet()) {
//             messageObject.addMessagePart(name, fields.get(name));
//         }

//         for (var name : buttons.keySet()) {
//             messageObject.addInlineKeybordButton(getInlineKeybordButton(name, buttons.get(name)));
//         }
        
//         messageObject.addInlineKeybordButton(getInlineKeybordButton("Добавить", "addfeature"));

//         responseObject.addMessageObject(messageObject);

//         return responseObject;
//     }
// }
