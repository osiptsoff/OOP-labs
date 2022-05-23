package classes;

public class IncorrectDataException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public IncorrectDataException() { super("Были введены некорректные данные.");}
}