package Practice;
import java.util.*;


public class arrays {
    public static void main(String[] args) {
        String input = "AjfafbUIOadfsge";

        StringBuilder vowels = new StringBuilder();
        StringBuilder consonants = new StringBuilder();

        Set<Character> checker = Set.of('a','e','i','o','u','A','E','I','O','U');

        for(char n : input.toCharArray()){
            if(checker.contains(n)){
                vowels.append(n);
            }else {
                consonants.append(n);
            }
        }
        String output = vowels.toString() + consonants;
        System.out.println(output);
    }
}

