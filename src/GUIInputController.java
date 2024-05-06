import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class GUIInputController {
    @FXML
    private ImageView titleImageView;
    Image titleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/wordLadderTitlle.png")));
    public void displayTitleImage(){
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
        if(Astar.isSelected()){
            choice = "A*";
        } else if(UCS.isSelected()){
            choice = "UCS";
        } else if(GreedyBFS.isSelected()){
            choice = "Greedy Best First Search";
        } else if(SpeedyHeuristic.isSelected()){
            choice = "SpeedyHeuristic";
        } else {
            choice = "";
        }
        buttonOutputMessage.setText("Run the word ladder with " + choice );
    }


    public void handleRunButton(javafx.event.ActionEvent actionEvent) {
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
        List<String> path = searchBy.findPath(dictionary, visitedNode);

        // Display results
        if (path != null && !path.isEmpty()) {
            StringBuilder result = new StringBuilder();
            result.append("Path found: ");
            for (String word : path) {
                result.append(word).append(" ");
            }
            result.append("Total steps: ").append(path.size() - 1);
            buttonOutputMessage.setText(result.toString());
        } else {
            buttonOutputMessage.setText("No path found between the given words.");
        }
    }


//    // This method will be called when the "Go!" button is pressed
//    @FXML
//    private void handleGoButton() {
//        String start = startWord.getText();
//        String end = endWord.getText();
//        RadioButton selectedAlgorithm = (RadioButton) algorithmGroup.getSelectedToggle();
//
//        // Add code here to process the input data and start the search
//        System.out.println("Start Word: " + start);
//        System.out.println("End Word: " + end);
//        System.out.println("Selected Algorithm: " + selectedAlgorithm.getText());
//    }
}
