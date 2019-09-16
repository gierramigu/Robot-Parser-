public class OppFBNode implements SensorNode {

    public int execute(Robot robot){
        return robot.getOpponentFB();
    }

    public int evaluate(Robot robot ){
        return execute(robot);
    }

    public String toString(){
        return "oppFB";
    }

}
