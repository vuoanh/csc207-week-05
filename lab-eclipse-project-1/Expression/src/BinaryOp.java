
public abstract class BinaryOp implements Expression{
	private Expression lhs;
	private Expression rhs;
	private char opToken;
	
	public BinaryOp (Expression lhs, Expression rhs, char opToken){
		this.lhs = lhs;
		this.rhs = rhs;
		this.opToken = opToken;
	}
	public  abstract int binOp(Expression x, Expression y);
	
	
	public int eval() {
		return binOp(lhs, rhs);
	}
	
	public String toString() {
		return "(" + lhs.toString() + opToken + rhs.toString() + ")" ;
	}
	
}
