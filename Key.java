package hangman;

public class Key {
	private String mykey;
	private int count;

	public Key() {
		// TODO Auto-generated constructor stub
		setCount(0);
		
	}
	public Key(int size) {
		// TODO Auto-generated constructor stub
		setCount(0);
		StringBuilder sb = new StringBuilder("");
		for (int x = 0; x < size; x += 1){
			sb.append("-");
		}
		mykey = sb.toString();
	}
	
	public Key(String wordin, char letterin){
		setCount(0);
		StringBuilder sb = new StringBuilder("");
		for (int x = 0; x < wordin.length(); x+=1){
			if (wordin.charAt(x) == letterin){
				sb.append(letterin);
				setCount(getCount() + 1);
			} else {
				sb.append("-");
			}
		}
		mykey = sb.toString();
	}
	
	@Override
	public String toString(){
		return mykey.toString();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object in){
		Key other = (Key) in;
		if (this.toString().equals(other.toString())){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		int value = 17;
		value = 31 * value + (mykey.hashCode() + count);
		return value;
	}
	
	public void addletters(Key in){
		StringBuilder sb = new StringBuilder(mykey);
		for (int x = 0; x < mykey.length(); x += 1){
			if (this.mykey.charAt(x) == '-') {
				if(in.mykey.charAt(x) != '-') {
					sb.setCharAt(x, in.mykey.charAt(x));
				}
			}
		}
		mykey = sb.toString();
	}
	
	public void addletters(String in){
		this.mykey = in;
	}
	
	public boolean checkIfWordFull(){
		for (int x = 0; x < mykey.length(); x += 1){
			if (mykey.charAt(x) == '-') {return false;}
		}
			return true;
	}
	
	public int compareTo(Key in){
		if (this.getCount() > in.getCount()){
			return 1;
		} 
		if (this.getCount() < in.getCount()){
			return -1;
		}
		for (int x = this.mykey.length()-1; x>=0;x-=1){
			if (this.mykey.charAt(x) != '-'){
				if (in.mykey.charAt(x) != '-'){
					
				} else {
					return -1;
				}
			} else {
				if (in.mykey.charAt(x) != '-'){
					return 1;
				}
			}
		}
		return 0;
		
	}

}
