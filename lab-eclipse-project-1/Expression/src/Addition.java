
public class Addition extends BinaryOp{
	private Expression lhs;
	private Expression rhs;
	
	public Addition(Expression lhs, Expression rhs, char opToken) {
		super(lhs, rhs, '+');
	}

	@Override
	public int binOp(Expression x, Expression y) {
		return x.eval() + y.eval();
	}
}
