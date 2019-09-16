
import java.util.ArrayList;

public class MoveNode implements RobotProgramNode {
    private int count;
    private ExpressionNode expr;

    public MoveNode(){ this.count = count;}

    public MoveNode(ExpressionNode expr){
        this.expr = expr;
    }

    public String toString(){
        if(expr ==null) {
            return "move";
        }
        return expr.toString() + " = " + "number of times moved";
    }

    public void execute(Robot robot){
        if(expr ==null ) { robot.move(); }
        else {
            count = expr.evaluate(robot);
            for (int i = 0; i < count; i++) {
                robot.move();
            }
        }

    }
}
