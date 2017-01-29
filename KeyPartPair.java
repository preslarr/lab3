package hangman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeyPartPair {

	private Map<Key, Partition> mymap;

	public KeyPartPair() {
		// TODO Auto-generated constructor stub
		mymap = new HashMap<Key, Partition>();
	}

	public KeyPartPair(Partition partin, char letterin) {
		mymap = new HashMap<Key, Partition>();
		for (String s : partin.words) {
			Key akey = new Key(s, letterin);
			if (mymap.containsKey(akey)) {
				mymap.get(akey).add(s);
			} else {
				mymap.put(akey, new Partition());
				mymap.get(akey).add(s);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (Map.Entry<Key, Partition> entry : mymap.entrySet()) {
			sb.append("\n<" + entry.getKey().toString() + ">\n");
			sb.append(entry.getValue().toString());
		}
		return sb.toString();
	}

	public Set<String> getBestSet() {
		ArrayList<Key> keyset = new ArrayList<>();
		int max = 0;
		for (Map.Entry<Key, Partition> entry : mymap.entrySet()) {
			if (entry.getValue().words.size() > max){
				max = entry.getValue().words.size();
				keyset.clear();
				keyset.add(entry.getKey());
			} else if (entry.getValue().words.size() == max) {
				keyset.add(entry.getKey());
			} else {
				
			}
		}
		if (keyset.size() == 1) {
			
			return mymap.get(keyset.get(0)).words;
		} else {
			Key bestkey = lookAtLetters(keyset);
			return mymap.get(bestkey).words;
		}
	}
	
//	public Key findBestKey(ArrayList<Key> in){
//		ArrayList<Key> keyset = new ArrayList<>();
//		int min = 20;
//		for (Key k : keyset) {
//			if (k.getCount() < min){
//				min = k.getCount();
//				keyset.clear();
//				keyset.add(k);
//			} else if (k.getCount() == min) {
//				keyset.add(k);
//			} else {
//				
//			}
//		}
//		if (keyset.size() == 1) {
//			
//			return keyset.get(0);
//		} else {
//			return lookAtLetters(keyset);
//		}
//		
//		
//	}
	
	public Key lookAtLetters(ArrayList<Key> in){
		Key ptr;
		ptr = in.get(0);
		for (int x = 1; x < in.size(); x += 1){
			if (ptr.compareTo(in.get(x)) > 0){
				ptr = in.get(x);
			}
			
		}
		return ptr;
	}
	
	

}
