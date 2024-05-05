import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class GreedyBestFirst extends Algorithm {
    public GreedyBestFirst(String start, String end) {
        super(start, end);
    }

    @Override
    public void findAndAddChildren(Node node, Dictionary dict){
        HashSet<String> oneDifferenceWords = dict.getOneDifferenceWords(node.getWordName());
        for (String childWord: oneDifferenceWords){
            Node child = new Node(childWord);
            int hScore = dict.countLetterDifference(childWord, this.getEnd());
            child.setFScore(hScore);
            node.addChild(child);
        }
    }

    @Override
    public ArrayList<String> findPath(Dictionary dict){
        Node root = new Node(this.getStart());
        root.setFScore(0);
        HashSet<String> visitedNode = new HashSet<>();
        ArrayList<Node> toVisitNode = new ArrayList<Node>();
        Node currentNode = root;

        Node endNode = null;
        while (endNode==null){
            if (currentNode.getWordName().equals(this.getEnd())){
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
                toVisitNode.clear();
            }
        }
        return endNode.backtrack();
    }
}
