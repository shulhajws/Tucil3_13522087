import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class Dictionary {
    private HashSet<String> words;
    private HashMap<Integer, HashSet<String>> wordLengthMap;

    public Dictionary(int n) {
        words = new HashSet<>();
        wordLengthMap = new HashMap<>();
        loadWords(n);
    }

    // Load Dictionary of Length n
    private void loadWords(int n) {
        // Adjusted to specify a path from the project root
        String folderPath = "dictionary";
        String fileName = n + "-letter-words.txt";
        String filePath = folderPath + File.separator + fileName;
        System.out.println("File Path: " + filePath); // Print file path for debugging

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String word;
            while ((word = reader.readLine()) != null) {
                if (word.length() == n) {
                    words.add(word.toUpperCase()); // Store in lowercase for case-insensitive comparison
                }
            }
            reader.close();

            // Preprocess the dictionary to group words by their length
            wordLengthMap.put(n, new HashSet<>(words));
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }

    public HashSet<String> findOneDifferenceWords(String wordName) {
        HashSet<String> oneDifferenceWords = new HashSet<>();
        for (String word : this.words) {
            if (isDifferentByOneLetter(wordName, word)) {
                oneDifferenceWords.add(word);
            }
        }
        // System.out.println(oneDifferenceWords);
        return oneDifferenceWords;
    }

    public boolean isDifferentByOneLetter(String word1, String word2) {
        return countLetterDifference(word1, word2) == 1;
    }

    public int countLetterDifference(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount;
    }

    public static void main(String[] args) {
        // Example usage
        Dictionary dictionary = new Dictionary(3);
        System.out.println("Is 'asd' a valid word? " + dictionary.isValidWord("BAT"));
        System.out.println("Is 'bad' a valid word? " + dictionary.isValidWord("bad"));
    }
}
