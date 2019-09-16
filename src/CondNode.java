public class CondNode implements ConditionalNode {
    ConditionalNode n;
    public CondNode(ConditionalNode n ){ this.n = n;}

    public boolean evaluate(Robot robot){
        return n.evaluate(robot); //Recursive DFS evaluation of expression tree
    }

    public String toString(){
        return n.toString();
    }

    //this could cause an error as it shouldn't be here
    public void execute (Robot robot){
       // n.execute(robot);
    }



}
