import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class WordLadder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Welcome to the Word Ladder Solver!");

            // Get user input for start and end words
            System.out.print("Enter start word: ");
            String start = scanner.nextLine().toUpperCase();
            System.out.print("Enter end word: ");
            String end = scanner.nextLine().toUpperCase();

            if (start.length() != end.length()){
                System.err.println("Both words must be the same length");
                return;
            }

            if (start.length() <2 || start.length()>15){
                System.err.println("Valid words are within 2 to 15 characters");
                return;
            }
            
            // Load the dictionary
            Dictionary dictionary = new Dictionary(start.length());

            // Ensure both words are in the dictionary
            if (!dictionary.isValidWord(start) || !dictionary.isValidWord(end)) {
                System.err.println("Both words must be in the dictionary.");
                return;
            }

            // Algorithm selection
            System.out.println("Select the algorithm you want to search the word ladder by:");
            System.out.println("1. A* Search");
            System.out.println("2. Uniform Cost Search (UCS)");
            System.out.println("3. Greedy Best First Search");
            System.out.println("4. Speedy Heuristic (for learning and insight generation purposes)");
            System.out.print("Enter choice (1, 2, or 3): ");
            int algorithmChoice = scanner.nextInt();
            scanner.nextLine(); // consume the remaining newline

            Algorithm searchBy;
            switch (algorithmChoice) {
                case 1:
                    searchBy = new Astar(start, end);
                    break;
                case 2:
                    searchBy = new UCS(start,end);
                    break;
                case 3:
                    searchBy = new GreedyBestFirst(start, end);
                    break;
                case 4:
                    searchBy = new SpeedyHeuristic(start, end);
                    break;
                default:
                    System.err.println("Invalid algorithm choice.");
                    return;
            }

            // // Execute the selected algorithm
            long startTime = System.currentTimeMillis();
            HashSet<String> visitedNode = new HashSet<>();
            List<String> path = searchBy.findPath(dictionary, visitedNode);
            int numOfNodeVisited = visitedNode.size();
            long endTime = System.currentTimeMillis();

            // Output results
            if (path != null && !path.isEmpty()) {
                System.out.println("Path found:");
                path.forEach(System.out::println);
                System.out.println("Total steps: " + (path.size() - 1));
                System.out.println("Total node visited: " + numOfNodeVisited);
            } else {
                System.out.println("No path found between the given words.");
            }
            System.out.println("Execution time: " + (endTime - startTime) + " ms");

        } finally {
            scanner.close();
        }
    }
}
