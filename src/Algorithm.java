import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

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

    public ArrayList<String> findPath(Dictionary dict, HashSet<String> visitedNode){
        Node root = new Node(this.start);
        root.setFScore(0);
        PriorityQueue<Node> toVisitNode = new PriorityQueue<>(new Node.NodeComparator());;
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
                currentNode = toVisitNode.poll();
            }
        }
        return endNode.backtrack();
    }
}
