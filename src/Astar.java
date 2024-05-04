import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Astar extends Algorithm {
    private String start;
    private String end;

    public Astar(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void findAndAddChildren(Node node, Dictionary dict){
        // System.out.println("masuk find add children");
        HashSet<String> oneDifferenceWords = dict.findOneDifferenceWords(node.getWordName());
        for (String childWord: oneDifferenceWords){
            Node child = new Node(childWord);
            child.setFScore(node.getFScore()+1);
            int hScore = dict.countLetterDifference(childWord, this.end);
            child.addFScore(hScore);
            node.addChild(child);
        }
        // node.printChildren();
    }

    @Override
    public ArrayList<String> findPath(Dictionary dict){
        Node root = new Node(this.start);
        root.setFScore(0);
        HashSet<String> visitedNode = new HashSet<>();
        ArrayList<Node> toVisitNode = new ArrayList<Node>();
        Node currentNode = root;

        Node endNode = null;
        while (endNode==null){
            if (currentNode.getWordName().equals(this.end)){
                endNode = currentNode;
            } else {
                visitedNode.add(currentNode.getWordName());
                findAndAddChildren(currentNode, dict);
                // System.out.println("child added");
                for (Node child: currentNode.getChildren()){
                    if (!visitedNode.contains(child.getWordName())){
                        toVisitNode.add(child);
                    }
                }

                toVisitNode.sort(Comparator.comparingInt(Node::getFScore));
                currentNode = toVisitNode.remove(0);
            }

        }
        ArrayList<String> path = endNode.backtrack();
        return path;
    }
}
