import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GUIOutputController {
    @FXML
    private TextFlow pathResultTextFlow;

    @FXML
    private Label durationLabel;

    @FXML
    private Label visitedNodeLabel;

    @FXML
    private Label totalStepsLabel;

    public void initialize(String pathResult, long duration, int numOfVisitedNodes, int totalSteps) {
        // Clear any existing content in the TextFlow
        pathResultTextFlow.getChildren().clear();

        // Split the pathResult string into individual words
        String[] words = pathResult.split("\n");

        // Add each word to the TextFlow with a newline separator
        for (String word : words) {
            Text text = new Text(word + "\n");
            pathResultTextFlow.getChildren().add(text);
        }

        // Set the text of the duration and visited nodes labels
        durationLabel.setText("Duration: " + duration + " ms");
        visitedNodeLabel.setText("Visited Nodes: " + numOfVisitedNodes);
        totalStepsLabel.setText("Total Steps: " + totalSteps);
    }
}
