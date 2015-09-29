public class Negate implements Expression{
	private Expression a;
	
	public int eval() {
		return -a.eval();
	}
	
	public String toString() {
		return "(" + "-" + a.toString() + ")";
	}

}
