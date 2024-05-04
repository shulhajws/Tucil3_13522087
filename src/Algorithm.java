import java.util.ArrayList;

public abstract class Algorithm {

    // this method will be overriden
    public abstract ArrayList<String> findPath(Dictionary dict);

    // this method add child to the current node. child is a word, which differs only by one letter away
    public abstract void findAndAddChildren(Node node, Dictionary dict);
}
