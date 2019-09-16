
public class WhileNode implements RobotProgramNode{
    private ConditionalNode cond;
    private BlockNode block;

    public WhileNode(ConditionalNode cond){
        this.cond = cond;
    }

    public void block(BlockNode block){
        this.block = block;
    }

    public String toString(){

        return "while (" +  cond.toString() + ")" + block.toString() + "end of while";
    }
    public void execute(Robot robot){
        //recursing down
        while(cond.evaluate(robot)){
            block.execute(robot);
        }
    }



}
