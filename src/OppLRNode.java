public class OppLRNode implements SensorNode {

    public int execute(Robot robot){
        return robot.getOpponentLR();
    }

    public int evaluate(Robot robot){
        return execute(robot);
    }

    public String toString(){
        return "oppLR";
    }
}
