import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("enable1.txt");
        ArrayList<String> words = constructList(file);
        words = listCompactor(words);
        Scanner inputReader = new Scanner(System.in);
        boolean inputCheck=false;
        for(String word : words)
            System.out.println(word.toUpperCase());
        Random generator = new Random(System.currentTimeMillis());
        String referenceWord = words.get(generator.nextInt(words.size()));
        for(int i = 4; i > 0;i--){
            System.out.print("Guess ("+i+" left)? ");
            String inputString=inputReader.next();
            while(inputString.length()!=referenceWord.length()){
                System.out.print("Invalid entry. Try again: ");
                inputString=inputReader.next();
            }
            inputCheck = inputChecker(inputString, referenceWord);
            if(inputCheck)
                break;
        }
        if(inputCheck){
            System.out.println("You win!");
        } else {
            System.out.println("You lose :(\nThe word was "+referenceWord);
        }

    }

    public static ArrayList<String> constructList(File file) {

        BufferedReader reader = null;
        ArrayList<String> words = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            words = new ArrayList<String>();
            while ((text = reader.readLine()) != null) {
                if (text.length() == 8) {
                    words.add(text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    public static  ArrayList<String> listCompactor(ArrayList<String> wordList){

        ArrayList<String> newList = new ArrayList<String>(10);
        Random randomWord = new Random(System.currentTimeMillis());
        for(int i=0;i<10;i++) {
            int randomPosition = randomWord.nextInt(wordList.size());
            newList.add(wordList.get(randomPosition));
            wordList.remove(randomPosition);
        }
        return newList;
    }

    public static boolean inputChecker(String input, String referenceWord){

        int correctLetters=0;
        input = input.toLowerCase();
        System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == referenceWord.charAt(i))
                correctLetters++;
        }
        System.out.println(correctLetters+"/8 correct");
        return (correctLetters==8);
    }
}
