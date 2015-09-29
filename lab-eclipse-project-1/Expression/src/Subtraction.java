
public class Subtraction implements Expression{
	private Expression a;
	private Expression b;
	
	public int eval(){
		return a.eval() - b.eval();
	}
	
	public String toString() {
		return "(" + a.toString() + "-" + b.toString()  + ")";
	}
}
