package hangman;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Partition {
	public Set<String> words;

	public Partition() {
		// TODO Auto-generated constructor stub
		words = new TreeSet<String>();
	}

	public void add(String in) {
		words.add(in.toLowerCase());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (String s : words) {
			sb.append(s + "\n");
		}
		return sb.toString();
	}
	
	public void setWordLength(int len){
		Set<String> rightsize = new TreeSet<>();
		for (String s : words){
			if (s.length() == len){
				rightsize.add(s);
			}
		}
		words = rightsize;
	}
	
	public Set<String> getWords(){
		return words;
	}
	
	public Key getPartsKey(char letter){
		if (words.isEmpty()){return null;}
		for (String s : words){
			Key temp = new Key(s, letter);
			return temp;
		}
		return null;
	}
	
	public String getRandWord(){
		for (String s : words){
			return s;
		}
		return " ";
	}
	

}
