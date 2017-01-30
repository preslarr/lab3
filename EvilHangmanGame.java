package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EvilHangmanGame implements IEvilHangmanGame {
	private KeyPartPair kpp;
	public Partition mypart;
	private int gcount;
	private ArrayList<String> guessedletters = new ArrayList<>();
	public Key gamekey;
	public boolean winstatus = false;

	public EvilHangmanGame() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void startGame(File dictionary, int wordLength) {
		// TODO Auto-generated method stub
		// check wordlength ********************* >= 2
		setGcount(0);
		try {
			Scanner sc = new Scanner(dictionary);
			mypart = new Partition();
			while (sc.hasNext()) {
				mypart.add(sc.next());
			}
			sc.close();

			mypart.setWordLength(wordLength);
			gamekey = new Key(wordLength);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
		// TODO Auto-generated method stub
		this.checkGuessMade(guess);
		kpp = new KeyPartPair(mypart, guess);
		Set<String> output = new TreeSet<>();
		output = kpp.getBestSet();
		addGuessedLetter(guess);
		//System.out.print(output);
		return output;
	}

	public int getGcount() {
		return gcount;
	}

	public void setGcount(int gcount) {
		this.gcount = gcount;
	}

	public void addGuessedLetter(char in) {
		String temp = Character.toString(in);
		guessedletters.add(temp);
	}

	public void addGuessedLetter(String in) {
		guessedletters.add(in);
	}

	public String printGuessedLetters() {
		StringBuilder sb = new StringBuilder();
		Collections.sort(guessedletters);
		for (String s : guessedletters) {
			sb.append(s + " ");
		}
		return sb.toString();
	}

	public void checkGuessMade(char in) throws GuessAlreadyMadeException {
		for (int x = 0; x < guessedletters.size(); x += 1) {
			if (guessedletters.get(x).charAt(0) == in) {
				throw new GuessAlreadyMadeException();
			}
		}
	}

	public boolean gameRunning(EvilHangmanGame game) {
		if (game.getGcount() == 0) {
			return false;
		}
		if (game.gamekey.checkIfWordFull()) {
			winstatus = true;
			return false;
		}
		if (game.winstatus) {
			return false;
		}

		return true;
	}

	public boolean checkGuessValid(String in) {
		if (in.length() != 1) {
			return false;
		}
		char dachar = in.charAt(0);
		if (!Character.isLetter(dachar)) {
			return false;
		}

		return true;
	}

	public int haveTheyWon(char letter) {
		Key partitionkey = mypart.getPartsKey(letter);
		if (partitionkey == null) {
			winstatus = true;
			return 0;
		} else if (partitionkey.getCount() > 0) {
			gamekey.addletters(partitionkey);
			return partitionkey.getCount();
		} else {
			gcount -= 1;
			return -1;
		}
	}
}
