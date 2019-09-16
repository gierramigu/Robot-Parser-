public class OrNode implements ConditionalNode {
    private ConditionalNode node1;
    private ConditionalNode node2;

    public OrNode(ConditionalNode node1, ConditionalNode node2){
        this.node1 = node1;
        this.node2 = node2;
    }

    public String toString() {
        return node1.toString() + "or" + node2.toString();
    }

    public boolean evaluate(Robot robot){
        return (node1.evaluate(robot) || node2.evaluate(robot));
    }

}
