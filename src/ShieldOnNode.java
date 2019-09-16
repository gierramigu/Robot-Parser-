public class ShieldOnNode implements RobotProgramNode{

    public String toString(){
        return "shieldOn";
    }

    public void execute(Robot robot){
        robot.setShield(true);
    }


}
