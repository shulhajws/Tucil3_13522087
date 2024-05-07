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
        String folderPath = "src" + File.separator + "dictionary";
        String fileName = n + "-letter-words.txt";
        String filePath = folderPath + File.separator + fileName;
        System.out.println("File Path: " + filePath); // Print file path for debugging

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            loadWordsFromReader(reader, n);
            reader.close();
        } catch (IOException e) {
            // Try loading from another path
            folderPath = "dictionary";
            filePath = folderPath + File.separator + fileName;
            System.out.println("File Path: " + filePath); // Print file path for debugging
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                loadWordsFromReader(reader, n);
                reader.close();
            } catch (IOException ex) {
                System.err.println("Error loading dictionary: " + ex.getMessage());
            }
        }
    }

    private void loadWordsFromReader(BufferedReader reader, int n) throws IOException {
        String word;
        while ((word = reader.readLine()) != null) {
            if (word.length() == n) {
                words.add(word.toUpperCase());
            }
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
