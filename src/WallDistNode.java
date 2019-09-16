public class WallDistNode implements SensorNode, ExpressionNode{

    public int evaluate(Robot robot){
        return execute(robot);
    }

    public String toString(){
        return "wallDist";
    }

    public int execute(Robot robot){
        return robot.getDistanceToWall();
    }


}
