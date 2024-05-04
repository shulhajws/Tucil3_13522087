import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public abstract class Algorithm {
    private String start;
    private String end;

    public Algorithm(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart(){
        return this.start;
    }
    public String getEnd(){
        return this.end;
    }


    // this method add child to the current node. child is a word, which differs only by one letter away
    public abstract void findAndAddChildren(Node node, Dictionary dict);

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
