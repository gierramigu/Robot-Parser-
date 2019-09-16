public class AndNode implements ConditionalNode {
    private ConditionalNode node1;
    private ConditionalNode node2;

    public AndNode (ConditionalNode n1, ConditionalNode n2){
        this.node1 = n1;
        this.node2 = n2;
    }


    public String toString (){
        return node1.toString() + "and" + node2.toString();
    }

    public boolean evaluate(Robot robot){
        return(node1.evaluate(robot)&& node2.evaluate(robot));
    }




}
