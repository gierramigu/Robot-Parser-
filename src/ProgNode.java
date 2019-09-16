import java.util.ArrayList;

public class ProgNode implements RobotProgramNode {

    private ArrayList<RobotProgramNode> children; //statement Nodes

    public ProgNode(){
        children = new ArrayList<RobotProgramNode>();
    }
    public String toString(){
        String result = "";
        for(RobotProgramNode n: this.children){
            result +=  n.toString() + "\n";
        }
        return result;
    }

    public void execute(Robot robot){
        for(RobotProgramNode n : this.children ){
            System.out.println("Number of children: " + children.size());
            n.execute(robot);
        }
    }

    public void addNode(RobotProgramNode node){
        children.add(node);
    }
}
