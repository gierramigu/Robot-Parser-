public class TurnRNode implements RobotProgramNode {

    //empty constructor
    public TurnRNode(){}

    public void execute(Robot robot ){
        robot.turnRight();
    }

    public String toString(){ return "turnR"; }

}
