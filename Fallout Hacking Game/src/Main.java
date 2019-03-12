import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    ArrayList<String> dict = new ArrayList<>();
    ArrayList<String> words = new ArrayList<>();

    boolean isCorrectWord = false;
    String secretWord;
    int lives = 4;

    public Main() {
        processDictionary();
        menu();
        play();
    }

    public static void main(String[] args) {
        Main app = new Main();
    }

    public void menu() {
        int max;
        int min;
        int rand;
        System.out.println("Please select a difficulty from 1 - 5: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                min = 4;
                max = 7;
                rand = random.nextInt(max - min + 1) + min;
                System.out.println("Word length is " + rand + " & number of words is " + 5);
                pullWords(rand, 7);
                break;
            case 2:
                min = 4;
                max = 9;
                rand = random.nextInt(max - min + 1) + min;
                System.out.println("Word length is: " + rand + " & number of words is: " + 7);
                pullWords(rand, 9);
                break;
            case 3:
                min = 4;
                max = 11;
                rand = random.nextInt(max - min + 1) + min;
                System.out.println("Word length is: " + rand + " & number of words is: " + 9);
                pullWords(rand, 11);
                break;
            case 4:
                min = 4;
                max = 13;
                rand = random.nextInt(max - min + 1) + min;
                System.out.println("Word length is: " + rand + " & number of words is: " + 12);
                pullWords(rand, 13);
                break;
            case 5:
                min = 4;
                max = 13;
                rand = random.nextInt(max - min + 1) + min;
                System.out.println("Word length is: " + rand + " & number of words is: " + 15);
                pullWords(rand, 15);
                break;
            default:
                System.out.println("Please select a valid option");
                break;
        }
    }

    private void play() {

        System.out.println("\nEnter a word: ");
        String word = scanner.next();
        while (lives !=0) {
            if(guessWord(word, secretWord)) {
                System.out.println("Machine hacked...");
                System.exit(0);
                break;
            }
            else {
                lives--;
                play();
            }
        }
        System.out.println("Machine locked, exiting...");
        System.exit(0);

    }

    private void processDictionary() {
        try {
            FileReader reader = new FileReader("dictionary.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dict.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pullWords(int wordLength, int wordCount) {
        List<String> list = dict;
        Collections.shuffle(list);
        for (int j = 0; j < wordCount; j++) {
            for (String word : list) {
                if (word.length() == wordLength) {
                    words.add(word);
                    list.remove(word);
                    break;
                }
            }
        }
        System.out.println("-----");
        System.out.println("\nYour words are: ");
        for (int l = 0; l < words.size(); l++) {
            System.out.println(words.get(l));
        }
        int rand = random.nextInt(words.size() + 1);
        secretWord = words.get(rand);
       // System.out.println("\nSecret word " + secretWord);
    }

    private boolean guessWord(String word, String secretWord) {
        if(word.equals(secretWord)) {
            isCorrectWord = true;
        }
        else {
            int counter = 0;
            char[] guessCharacters = word.toCharArray();
            char[] secretCharacters = secretWord.toCharArray();
            for (char c : secretCharacters) {
                for (char d : guessCharacters) {
                    if (c == d) {
                        counter++;
                    }
                }
            }
            System.out.println(counter + "/" + secretCharacters.length + " letters correct");
            isCorrectWord = false;
        }
        return isCorrectWord;
    }
}
