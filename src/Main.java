import java.io.IOException;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Main extends Application{
    public static void main(String[] args) throws IOException, URISyntaxException{
        launch(args);
    }

    public void start(Stage primaryStage) {
        NodeList<String> people = new NodeList<String>();
        Group root = new Group();
        primaryStage.setTitle("Secret Santa");

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");

        Button addButton = new Button("Add");

        addButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                Text newPersonLabel = new Text(nameTextField.getText() + ":" + emailTextField.getText());
                root.getChildren().add(newPersonLabel);
                newPersonLabel.relocate(0, 20 * people.size() + 50);
                people.addFirst(emailTextField.getText(), nameTextField.getText());
            }
        });

        Button generateButton = new Button("Pair People");

        generateButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                try {
                    people.pairPeople();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        root.getChildren().add(nameTextField);
        root.getChildren().add(emailTextField);
        emailTextField.relocate(180, 0);
        root.getChildren().add(addButton);
        addButton.relocate(360, 0);
        root.getChildren().add(generateButton);
        generateButton.relocate(460, 0);


        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

}