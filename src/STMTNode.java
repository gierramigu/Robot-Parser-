
public class STMTNode implements RobotProgramNode{
    RobotProgramNode child; //either Act or Loop node for minimum

    public STMTNode(RobotProgramNode node){
        this.child = node;
    }

    public String toString(){
        return this.child.toString();
    }

    public void execute(Robot robot ){
        child.execute(robot);
    }

}
