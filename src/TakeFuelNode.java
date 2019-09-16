public class TakeFuelNode implements RobotProgramNode {

    //empty constructor
    public TakeFuelNode(){ }

    public void execute(Robot robot){
        robot.takeFuel();
    }

    public String toString(){ return "takeFuel"; }


}
