public class ShieldOffNode implements RobotProgramNode {

    public String toString(){
        return "shieldOff";
    }

    public void execute (Robot robot){
        robot.setShield(false);
    }

}
