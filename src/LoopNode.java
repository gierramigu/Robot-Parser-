public class LoopNode implements RobotProgramNode {

    private RobotProgramNode block; //child of loop is non terminal block

    public LoopNode(RobotProgramNode node){
        this.block = node;
    }

    @Override
    //returns a textual representation of the LoopNode
    public String toString(){
        return block.toString();
    }

    @Override
    //Will call the relevant method from the Robot Class on the given robot.
    public void execute(Robot r){
        while(true) {
            this.block.execute(r);
        }
    }



}
