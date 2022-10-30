package model;

public class EncodingStrategiesFactory {
	public EncodingStrategiesFactory(){}
	public EncodingStrategy create (String strategy) {
		switch (strategy) {
		case "ROT13": return new EncodingROT13();
		case "Atbash": return new EncodingAtbash();
		default: return null;
		}
	}
}