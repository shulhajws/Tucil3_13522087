import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class Dictionary {
    private HashSet<String> words;

    public Dictionary(int n) {
        words = new HashSet<>();
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
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }

    public HashSet<String> getOneDifferenceWords(String wordName) {
        HashSet<String> validWords = new HashSet<>();
        char[] wordChars = wordName.toCharArray();
        
        for (int i = 0; i < wordChars.length; i++) {
            char originalChar = wordChars[i];
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != originalChar) {
                    wordChars[i] = c;
                    String newWord = new String(wordChars);
                    if (isValidWord(newWord)) {
                        validWords.add(newWord);
                    }
                }
            }
            wordChars[i] = originalChar;
        }
        return validWords;
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

}
