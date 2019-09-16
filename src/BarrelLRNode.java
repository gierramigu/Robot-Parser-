public class BarrelLRNode implements SensorNode, ExpressionNode {
    //Note: expression nodes can have sensor or num nodes
    //but the expression are for stage two
    private ExpressionNode expr;
    public BarrelLRNode(){}

    public BarrelLRNode(ExpressionNode expr){
        this.expr = expr;
    }

    public int evaluate(Robot robot){
        return execute(robot);
    }

    public String toString(){
        if(expr == null ){
            return "Closest barrel LR ";
        }
        return "barrel LR";
    }

    public int execute(Robot robot){
        if(expr == null){
            return robot.getClosestBarrelLR();
        }
        return robot.getBarrelLR(this.expr.evaluate(robot));
    }
}
