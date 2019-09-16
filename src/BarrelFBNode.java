public class BarrelFBNode implements SensorNode, ExpressionNode {

    private ExpressionNode expr;
    public BarrelFBNode(){}

    public BarrelFBNode(ExpressionNode expr){
        this.expr = expr;
    }

    public int evaluate(Robot robot){
        if(expr == null){
            return robot.getClosestBarrelFB();
        }
        return robot.getBarrelFB(this.expr.evaluate(robot));
    }

    public String toString(){
        if(expr == null){
            return "FB distance to closest barrel";
        }
        return "FB distance to barrel no. 'argument' ";
    }

    public int execute(Robot robot){
        return robot.getBarrelFB(expr.evaluate(robot));
    }
}
