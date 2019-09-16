public class TurnLNode implements RobotProgramNode {

    //empty constructor
    public TurnLNode(){}

    public void execute(Robot robot ){
        robot.turnLeft();
    }

    public String toString(){ return "turnL"; }

}
