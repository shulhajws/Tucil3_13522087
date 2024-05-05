import java.util.ArrayList;
import java.util.Collections;
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

    public void addFScore(int addfScore) {
        this.fScore += addfScore;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void printChildren(){
        for (Node child : this.children){
            System.out.println(child.getWordName());
        }
    }
}
