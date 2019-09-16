import java.util.ArrayList;

public class ActNode implements RobotProgramNode {

    private ArrayList<RobotProgramNode> children; //action nodes are its children

    public ActNode(){
        children = new ArrayList<>();
    }

    public String toString(){
        String result = "";
        for(RobotProgramNode n : children){
            result += n.toString();
        }
        return result + "";
    }

    public void execute (Robot robot) {
        for (RobotProgramNode n : children) {
            n.execute(robot);
        }
    }

}
