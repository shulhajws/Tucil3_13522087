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
}
