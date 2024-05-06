import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUISceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGUIOutput(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUIOutput.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGUIInput(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUIInput.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
