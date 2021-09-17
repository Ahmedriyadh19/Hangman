/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Collections;
import java.util.Vector;

/**
 *  
 * @author alahm
 */
//sengalton desgin pattern 
public class DifficultyLevel {
    private static DifficultyLevel instance = new DifficultyLevel();


    Vector<Vector<String>> words = new Vector<>();
    static int correctLetters;
    static int wrongLetters;

    private DifficultyLevel() {
        String easy = "bill hill bait rule tidy mail hall file fire turn half warn drug meat bald";
        String medium = "ceremony computer amputate cultural necklace positive workshop scenario freshman resource argument compound vertical advocate governor";
        String hard = "demonstrator spokesperson organisation discriminate architecture satisfaction intermediate acquaintance announcement compensation reproduction civilization relationship professional presidential";
        String[] holdEasy = easy.split(" ");
        String[] holdMedium = medium.split(" ");
        String[] holdHard = hard.split(" ");
        for (int i = 0; i < 3; i++) {
            this.words.add(new Vector<>());
        }
        Collections.addAll(words.get(0), holdEasy);
        Collections.addAll(words.get(1), holdMedium);
        Collections.addAll(words.get(2), holdHard);
    }
    
    public static DifficultyLevel getInstance() {
        return instance;
    }
    
    private Vector<String> levelChosen(String lv) {

        if (lv.equals("Easy")) {
            return words.get(0);
        } else if (lv.equals("Medium")) {

            return words.get(1);
        } else if (lv.equals("Hard")) {
            return words.get(2);
        }

        return words.elementAt(0);
    }

    private int random() {
        // define the range
        int max = 14;
        int min = 0;
        int range = max - min + 1;
        int rand;
        // generate random numbers within 1 to 10
        rand = (int) (Math.random() * range) + min;
        return rand;
    }

    private static void setCorrectLetters() {
        DifficultyLevel.correctLetters = 0;
    }

    private static void setWrongLetters() {
        DifficultyLevel.wrongLetters = 0;
    }

    public String getWord(String lv) {
        return levelChosen(lv).elementAt(random());
    }

    public String changToStars(String word) {
        StringBuilder stars = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            stars.append("*");
        }
        return stars.toString();
    }
// Convert string to char
    public Vector<Character> reVectoring(String input) {
        Vector<Character> x = new Vector<>();

        for (int i = 0; i < input.length(); i++) {
            x.add(input.charAt(i));
        }
        return x;
    }

    public String comparing(Vector<Character> userInput, Vector<Character> randomWord) {
        setCorrectLetters();
        setWrongLetters();
        
        // change random word of char to String
        //and then change the word to stars 
        //then change string to stars to char of stars
        Vector<Character> filterString = reVectoring(changToStars(convertToString(randomWord)));

        for (int i = 0; i < userInput.size(); i++) {
            int z = 0;
            for (int j = 0; j < randomWord.size(); j++) {

                if (userInput.elementAt(i).equals(randomWord.elementAt(j))) {
                    correctLetters++;
                    filterString.set(j, randomWord.elementAt(j));
                    z++;
                }
            }
            if (z == 0) {
                wrongLetters++;
            }
        }

        return convertToString(filterString);
    }

    public static int getCorrectLetters() {
        return correctLetters;
    }

    public static int getWrongLetters() {
        return wrongLetters;
    }

    public String convertToString(Vector<Character> co) {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < co.size(); i++) {
            x.append(co.elementAt(i));
        }
        return x.toString();
    }

}
