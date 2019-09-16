public class NumNode implements ExpressionNode {
    private int number;

    public NumNode(int num) {
        this.number = num;
    }

    public int evaluate() {
        return this.number;
    }

    public int evaluate(Robot robot) {
        return number;
    }

    public String toString() {
        return this.number + "";

    }

}