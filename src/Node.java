import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
    private String wordName;
    private Node parent;
    private int fScore;
    private List<Node> children;

    public Node(String wordName) {
        this.wordName = wordName;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            // Compare nodes based on their fScore
            return Integer.compare(node1.getFScore(), node2.getFScore());
        }
    }

    public ArrayList<String> backtrack() {
        Node node = this;
        ArrayList<String> path = new ArrayList<String>();
        while (node != null){
            path.add(node.wordName);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public String getWordName() {
        return wordName;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getFScore() {
        return this.fScore;
    }

    public void setFScore(int fScore) {
        this.fScore = fScore;
    }

    public List<Node> getChildren() {
        return children;
    }

}
