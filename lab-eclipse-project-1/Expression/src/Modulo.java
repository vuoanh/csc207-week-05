public class Modulo implements Expression{
	private Expression a;
	private Expression b;

	public Modulo(Expression a, Expression b){
		this.a = a;
		this.b = b;
	}
	public int eval(){
		return a.eval() % b.eval();
	}

	public String toString() {
		return "(" + a.toString() + "%" + b.toString()  + ")";
	}
}


