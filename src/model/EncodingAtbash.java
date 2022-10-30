package model;

import java.util.HashMap;

public class EncodingAtbash extends EncodingTemplate{
	private HashMap<Character, Character> map = new HashMap<Character, Character>();
	
	public EncodingAtbash() {
		generateMap();
	}
	
	protected char mapCharacter(char c) {
		if(map.containsKey(c)) {
			c = map.get(c);
			return c;
		}else {
			return c;
		}
	}
	
	private void generateMap() {
		char j = 'z';
		for (char i = 'a'; i <= 'z'; i++) {
			map.put(i, j);
			j--;
		}
		char l = 'Z';
		for (char k = 'A'; k <= 'Z'; k++) {
			map.put(k, l);
			l--;
		}
	}
}
