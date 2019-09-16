public class TurnAroundNode implements RobotProgramNode {

    public String toString(){
        return "turnAround";
    }
    public void execute (Robot robot){
        robot.turnAround();
    }

}
