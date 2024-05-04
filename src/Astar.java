import java.util.HashSet;

public class Astar extends Algorithm {
    public Astar(String start, String end) {
        super(start, end);
    }

    @Override
    public void findAndAddChildren(Node node, Dictionary dict){
        // System.out.println("masuk find add children");
        HashSet<String> oneDifferenceWords = dict.getOneDifferenceWords(node.getWordName());
        for (String childWord: oneDifferenceWords){
            Node child = new Node(childWord);
            child.setFScore(node.getFScore()+1);
            int hScore = dict.countLetterDifference(childWord, this.getEnd());
            child.addFScore(hScore);
            node.addChild(child);
        }
    }
}
