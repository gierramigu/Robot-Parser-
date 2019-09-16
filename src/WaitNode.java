public class WaitNode implements RobotProgramNode{
    private ExpressionNode expr;
    private int count;
    //empty constructor
    public WaitNode(){ } //stage 1

    public WaitNode(ExpressionNode expr){ this.expr = expr; } //stage 2

    public void execute(Robot robot) {
        if (expr == null) { robot.idleWait(); }
        else{
            count = expr.evaluate(robot); //because its an expression
            for(int i = 0; i < count; i++){
                robot.idleWait();
            }
        }
    }

    public String toString(){
        if(expr == null){ return "wait"; }
        return "wait expr = number of times to wait";
    }

}
