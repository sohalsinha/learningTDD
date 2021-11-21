package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman {

	public Set<String> usedWordsSet = new HashSet<>();
	public List<String> wordsList = new ArrayList<>();

	/**countAlphabet() returns how many times the alphabet 
	 * appears in the word.
	 * @param word
	 * @param alphabet
	 * @return
	 */
	public int countAlphabet(String word, char alphabet) {
		int result = 0;
		for (char c : word.toCharArray()) {
			if (c == alphabet) result++;
		}
		return result;
	}

	/** fetchWord(requestedLength) returns a word of requestedLength 
	 * from the wordsList.
	 * It also ensures that the word was
	 * not fetched previously by checking
	 * with the usedWordsSet
	 * @param requestedLength
	 * @return
	 */
	public String fetchWord(int requestedLength) {
		String result = null;
		for (String word : wordsList) {
			if (word.length() != requestedLength) continue;
			else if (usedWordsSet.add(word)) {
				result = word; 
				break;
			}
		}
		return result;
	}

	/**loadWords() reads each line that has a word from WordSource.txt
	 * and adds each word to the wordsList
	 */
	public void loadWords() {
		String line;
		try( BufferedReader br = new BufferedReader(new FileReader("WordSource.txt"))) {

			while ((line = br.readLine()) != null) {
				wordsList.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**fetchClue() returns a string of same number of dashes 
	 * as the length of the word passed to it.
	 * @param word
	 * @return
	 */
	public String fetchClue(String word) {
		StringBuilder clue = new StringBuilder();
		for (int i = 0; i< word.length(); i++) {
			clue.append("-");
		}
		return clue.toString();
	}

	/**fetchClue() takes a word, its clue with some dashes, 
	 * and a character guessed by the player. 
	 * It returns a new clue that may or may not be different 
	 * from the clue depending on whether 
	 * the word contains the guessed character or not
	 * @param word
	 * @param clue
	 * @param guess
	 * @return
	 */
	public String fetchClue(String word, String clue, char guess) {
		if (guess >= 'A' && guess <= 'Z') guess += 32;
		if (guess <'a' || guess > 'z') 
			throw new IllegalArgumentException("Invalid character");
		
		StringBuilder newClue = new StringBuilder();
		for (int i = 0; i< word.length(); i++) {
			if (guess == word.charAt(i) && guess != clue.charAt(i))
			newClue.append(guess);
			else newClue.append(clue.charAt(i));
		}
		return newClue.toString();
	}
}
