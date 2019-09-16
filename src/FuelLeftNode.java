public class FuelLeftNode implements SensorNode {

    public int execute(Robot robot){
        return robot.getFuel();
    }

    public int evaluate (Robot robot){
        return execute(robot);
    }

    public String toString(){
        return "fuelLeft";
    }



}
