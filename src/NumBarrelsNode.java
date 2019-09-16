public class NumBarrelsNode implements ExpressionNode, SensorNode {

    public int evaluate(Robot robot){
        return execute(robot);
    }

    public String toString(){
        return "numBarrels";
    }

    public int execute(Robot robot){
        return robot.numBarrels();
    }

}
