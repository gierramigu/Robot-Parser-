public class LessThanNode implements ConditionalNode {


    private ExpressionNode expr1;
    private ExpressionNode expr2;

    public LessThanNode(ExpressionNode expr1, ExpressionNode expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public String toString(){
        return "(" + expr1.toString() + " > " + expr2.toString() + ")";
    }

    public boolean evaluate(Robot robot){
        if(expr1.evaluate(robot) < expr2.evaluate(robot)){
            return true;
        }
        return false;
    }




}