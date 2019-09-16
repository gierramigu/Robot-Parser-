import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class IfNode implements RobotProgramNode{
    private ConditionalNode cond;
    private BlockNode block;
    private BlockNode blockElse; //for stage 2
    private ArrayList<IfNode> elseList;


    public IfNode(ConditionalNode cond){
        this.cond = cond;
    }

    public void block (BlockNode block){
        this.block = block;
    }

    public void blockElse (BlockNode elseBlock ){
        this.blockElse = elseBlock;
    }

    public String toString(){
        String result = "";
        result += "if(" + cond.toString() +  "{" + block.toString() +  "\t" + "}";
        if(elseList!=null){
            for(IfNode n : elseList){
                result += "el" + n.toString();
            }
        }

        return result; //returns the processed ifNodes
    }


    public void execute (Robot robot){
        if(cond.evaluate(robot)){ block.execute(robot); }
        else{
            if(elseList != null){
                for(IfNode node: elseList){
                    node.execute(robot);
                    return;
                }
            }
            if(blockElse !=null){blockElse.execute(robot);}
        }
    }

    public void addElseBlock(IfNode node) {
        if (elseList == null) {
            this.elseList = new ArrayList<IfNode>();
        }

        this.elseList.add(node);
    }

}
