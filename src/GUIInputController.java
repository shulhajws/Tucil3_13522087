import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class GUIInputController {
    @FXML
    private ImageView titleImageView;
    Image titleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/wordLadderTitlle.png")));

    public void displayTitleImage() {
        titleImageView.setImage(titleImage);
    }

    @FXML
    private TextField startWord;
    @FXML
    private TextField endWord;
    @FXML
    private RadioButton Astar, UCS, GreedyBFS, SpeedyHeuristic;
    @FXML
    private ToggleGroup algo;
    @FXML
    private Label buttonOutputMessage;
    @FXML
    private Button runButton;

    public void showAlgorithmChoice(javafx.event.ActionEvent actionEvent) {
        String choice;
        if (Astar.isSelected()) {
            choice = "A*";
        } else if (UCS.isSelected()) {
            choice = "UCS";
        } else if (GreedyBFS.isSelected()) {
            choice = "Greedy Best First Search";
        } else if (SpeedyHeuristic.isSelected()) {
            choice = "SpeedyHeuristic";
        } else {
            choice = "";
        }
        buttonOutputMessage.setText("Run the word ladder with " + choice);
    }


    public void handleRunButton(javafx.event.ActionEvent actionEvent) throws IOException {
        // Retrieve input values
        String start = startWord.getText();
        String end = endWord.getText();

        // Determine selected algorithm
        Algorithm searchBy = null;
        if (Astar.isSelected()) {
            searchBy = new Astar(start, end);
        } else if (UCS.isSelected()) {
            searchBy = new UCS(start, end);
        } else if (GreedyBFS.isSelected()) {
            searchBy = new GreedyBestFirst(start, end);
        } else if (SpeedyHeuristic.isSelected()) {
            searchBy = new SpeedyHeuristic(start, end);
        } else {
            // Handle no algorithm selected
            buttonOutputMessage.setText("Please select an algorithm.");
            return;
        }

        // Execute algorithm
        Dictionary dictionary = new Dictionary(start.length());
        HashSet<String> visitedNode = new HashSet<>();
        long startTime = System.currentTimeMillis();
        List<String> path = searchBy.findPath(dictionary, visitedNode);
        int steps = path.size()-1;
        long endTime = System.currentTimeMillis();

        if (path == null || steps == 0) {
            buttonOutputMessage.setText("No path found.");
        } else {
            // Load output scene
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIOutput.fxml"));
                Parent root = loader.load();

                GUIOutputController outputController = loader.getController();

                StringBuilder result = new StringBuilder();
                for (String word : path) {
                    result.append(word).append("\n");
                }
                // Display output scene with results outside the loop
                outputController.initialize(result.toString(), (endTime - startTime), visitedNode.size(), steps);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}