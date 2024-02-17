import java.util.Arrays;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		hashTag = hashTag.toLowerCase();

		String []dictionary = readDictionary("dictionary.txt");

		//read dictionary
		/*System.out.println("Dictionary loaded successfully.");
        System.out.println("Dictionary length: " + dictionary.length);*/

		//check if word exist in dictionary
		/*System.out.println("Word '" + hashTag + "' exists: " + existInDictionary(hashTag, dictionary));*/

		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);		

		//linoy feedback: who said you have 3000 lines? why not using while loop?
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readString();

		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		//linoy feedback: what if dictionary is empty? contains will not work..  I suggest you to check if dictionary is not empty first.
		boolean wordExist = Arrays.asList(dictionary).contains(word);
		return wordExist;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		String word = "";
		boolean wordExist;

        for (int i = 1; i <= N; i++) {
			word = hashtag.substring(0,i);
			wordExist = existInDictionary(word, dictionary);
			if (wordExist){
				System.out.println(word);
				hashtag = hashtag.substring(i,N);
				breakHashTag (hashtag, dictionary);
				return;
			}
        }
		return;
    }

}
