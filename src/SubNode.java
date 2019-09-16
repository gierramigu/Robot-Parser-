public class SubNode implements ExpressionNode {

    private ExpressionNode expr1;
    private ExpressionNode expr2;

    public SubNode(ExpressionNode expr1, ExpressionNode expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public String toString(){
        return "(" + expr1.toString() + "-" + expr2.toString() + ")";
    }

    public int evaluate (Robot robot){
        return (expr1.evaluate(robot) - expr2.evaluate(robot));
    }

}
