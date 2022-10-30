package model;

import java.util.HashMap;

public class EncodingROT13 extends EncodingTemplate{
	private HashMap<Character, Character> map = new HashMap<Character, Character>();
	
	public EncodingROT13() {
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
		char j = 'n';
		for (char i = 'a'; i <= 'm'; i++) {
			map.put(i, j);
			j++;
		}
		j = 'a';
		for (char i = 'n'; i <= 'z'; i++) {
			map.put(i, j);
			j++;
		}
		char l = 'N';
		for (char k = 'A'; k <= 'M'; k++) {
			map.put(k, l);
			l++;
		}
		l = 'A';
		for (char k = 'N'; k <= 'Z'; k++) {
			map.put(k, l);
			l++;
		}
	}
}