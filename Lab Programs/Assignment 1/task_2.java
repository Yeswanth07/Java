import java.util.HashMap;

public class AlphabetWarGame {
    private HashMap<Character, Integer> leftStrengths;
    private HashMap<Character, Integer> rightStrengths;

    public AlphabetWarGame() {
        leftStrengths = new HashMap<>();
        rightStrengths = new HashMap<>();

        leftStrengths.put('w', 4);
        leftStrengths.put('p', 3);
        leftStrengths.put('b', 2);
        leftStrengths.put('s', 1);

        rightStrengths.put('m', 4);
        rightStrengths.put('q', 3);
        rightStrengths.put('d', 2);
        rightStrengths.put('z', 1);
    }

    public AlphabetWarGame(HashMap<Character, Integer> leftCustom, HashMap<Character, Integer> rightCustom) {
        this.leftStrengths = leftCustom;
        this.rightStrengths = rightCustom;
    }

    public String alphabetWar(String word) {
        int leftScore = 0;
        int rightScore = 0;

        for (char c : word.toCharArray()) {
            if (leftStrengths.containsKey(c)) {
                leftScore += leftStrengths.get(c);
            } else if (rightStrengths.containsKey(c)) {
                rightScore += rightStrengths.get(c);
            }
        }

        return getResult(leftScore, rightScore);
    }

    public String alphabetWar(String leftWord, String rightWord) {
        int leftScore = 0;
        int rightScore = 0;

        for (char c : leftWord.toCharArray()) {
            if (leftStrengths.containsKey(c)) {
                leftScore += leftStrengths.get(c);
            }
        }

        for (char c : rightWord.toCharArray()) {
            if (rightStrengths.containsKey(c)) {
                rightScore += rightStrengths.get(c);
            }
        }

        return getResult(leftScore, rightScore);
    }

    private String getResult(int leftScore, int rightScore) {
        if (leftScore > rightScore) {
            return "Left side wins!";
        } else if (rightScore > leftScore) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    public static void main(String[] args) {
        AlphabetWarGame game1 = new AlphabetWarGame();

        System.out.println(game1.alphabetWar("z"));        
        System.out.println(game1.alphabetWar("zdqmwpbs"));  
        System.out.println(game1.alphabetWar("wwwwwwz"));    

        HashMap<Character, Integer> customLeft = new HashMap<>();
        HashMap<Character, Integer> customRight = new HashMap<>();

        customLeft.put('a', 5);
        customRight.put('x', 5);

        AlphabetWarGame game2 = new AlphabetWarGame(customLeft, customRight);
        System.out.println(game2.alphabetWar("aaaa", "xxxx")); 
    }
}
