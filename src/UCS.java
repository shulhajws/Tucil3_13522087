import java.util.HashSet;

public class UCS extends Algorithm {
    public UCS(String start, String end) {
        super(start, end);
    }

    @Override
    public void findAndAddChildren(Node node, Dictionary dict){
        HashSet<String> oneDifferenceWords = dict.getOneDifferenceWords(node.getWordName());
        for (String childWord: oneDifferenceWords){
            Node child = new Node(childWord);
            child.setFScore(node.getFScore()+1);
            node.addChild(child);
        }
    }
}
