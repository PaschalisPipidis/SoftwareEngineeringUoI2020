package model;

public abstract class EncodingTemplate implements EncodingStrategy {
	public EncodingTemplate() {}

	public String encode(String text) {
		String encodedText = "";
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			c = mapCharacter(c);
			encodedText += c;
		}
		return encodedText;
	}
	
	protected abstract char mapCharacter(char c);
}
