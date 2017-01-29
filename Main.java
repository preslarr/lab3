package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		if (!checkInput(args)) {
			System.out.println("Invalid Input");
			return;
		}
		File thefile = new File(args[0]);
		int wordlen = Integer.parseInt(args[1]);
		int numofguesses = Integer.parseInt(args[2]);
		// check wordlength (>=2) and guesses (>= 1)
		EvilHangmanGame game = new EvilHangmanGame();
		game.startGame(thefile, wordlen);
		if (game.mypart.words.isEmpty()) {
			System.out.println("Invalid Input");
			return;
		}
		game.setGcount(numofguesses);
		Scanner cin = new Scanner(System.in);
		String input;

		// begin play loop**************
		while (game.gameRunning(game)) {
			System.out.println("Remaining Guesses: " + game.getGcount());
			System.out.println("Used Letters: " + game.printGuessedLetters());
			System.out.println("Word: " + game.gamekey.toString());
			System.out.print("Enter Letter: ");
			input = cin.nextLine().toLowerCase();
			if (game.checkGuessValid(input)) {
				char daletter = input.charAt(0);
				try {
					game.mypart.words = game.makeGuess(daletter);
				} catch (GuessAlreadyMadeException e) {
					// TODO Auto-generated catch block
					System.out.println("You already guessed that\n");
					continue;
				}
				int winnum = game.haveTheyWon(daletter);
				if (winnum < 0) {
					System.out.println("Sorry there are no " + input + "'s");
				} else if (winnum > 0) {
					System.out.println("Yes, there is " + winnum + " " + input);
				} else {
					System.out.println("You Win! " + game.gamekey.toString());
					return;
				}
			} else {
				System.out.println("Invalid Input");
			}

			System.out.print("\n");
		}
		// see if player won
		if (game.winstatus) {
			System.out.println("You Win! " + game.gamekey.toString());
		} else {
			System.out.println("You Lose! the word was: " + game.mypart.getRandWord());
		}

	}

	private static boolean checkInput(String[] in) {
		if (in.length != 3) {
			return false;
		}
		File thefile = new File(in[0]);
		try {
			int wordlen = Integer.parseInt(in[1]);
			int numofguesses = Integer.parseInt(in[2]);
			if (wordlen < 2) {
				return false;
			}
			if (numofguesses < 1) {
				return false;
			}

		} catch (NumberFormatException nfe) {
			return false;
		}
		try {
			Scanner sc = new Scanner(thefile);
			if (!sc.hasNext()) {
				return false;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			return false;
		}

		return true;
	}

}
