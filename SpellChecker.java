import java.util.Arrays;

public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");

		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {

		if (str.length() == 0)
			return null;
		else
			return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		
		if (word1.isEmpty()) {
             return word2.length();
         }

         if (word2.isEmpty()) {
             return word1.length();
         } 
		 word1 = word1.toLowerCase();


        int substitution = levenshtein(tail(word1), tail(word2)) 
          + costOfSubstitution(word1.charAt(0), word2.charAt(0));

         int insertion = levenshtein(word1, tail(word2)) + 1;

         int deletion = levenshtein(tail(word1), word2) + 1;

         return min(substitution, insertion, deletion);
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readString();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int dictSize = dictionary.length;
		String returnWord = new String();

		if (Arrays.asList(dictionary).contains(word)) {
			return word;
		} 

		int currDistance = levenshtein(word,dictionary[0]);
		int prevDistance = currDistance;
		returnWord = dictionary[0];

		for (int i = 1; i < dictSize; i++) {
			currDistance = levenshtein(word,dictionary[i]);

			if (currDistance <= prevDistance) {
				returnWord = dictionary[i];
				prevDistance = currDistance;
			}
			if (prevDistance <= threshold )
				return returnWord;
        }

		if (prevDistance > threshold )
			return word;
		else
			return returnWord;
	}

}
