
public class Literal implements Expression{
	private int value;
	
	public Literal(int value){
		this.value = value;
	}
	
	public int eval() {
		return value;
	}
	
	public String toString() {
		return Integer.toString(value);
	}

}
