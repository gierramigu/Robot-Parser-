public class NotNode implements ConditionalNode {
    private ConditionalNode node1;

    public NotNode(ConditionalNode node1){
        this.node1 = node1;
    }

    public String toString(){
        return node1.toString() + "not";
    }

    public boolean evaluate(Robot robot){
        return (!node1.evaluate(robot));
    }
}
