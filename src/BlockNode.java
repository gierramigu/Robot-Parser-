import javax.jws.Oneway;
import java.util.List;
import java.util.ArrayList;


public class BlockNode implements RobotProgramNode {

    private ArrayList<RobotProgramNode> children; //stmt nodes

    public BlockNode(){
        children = new ArrayList<RobotProgramNode>();
    }

    @Override
    public String toString(){
        String result = "\n";
        for(RobotProgramNode n : children ){
            result += "\t" + n.toString() + "\n";
        }
        return result;
    }

    @Override
    public void execute (Robot robot){
        for(RobotProgramNode n : children ){
            n.execute(robot);
        }
    }

    public void addNode(RobotProgramNode node){
        children.add(node);
    }

    //getter
    public List<RobotProgramNode> getChildren(){
        return children;
    }
}
