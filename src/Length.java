public class Length extends Vertex {
    protected int[] MidPointsX = new int[4];
    protected int[] MidPointsY = new int[4];
    protected double[] lengths = new double[]{0.0, 0.0, 0.0, 0.0};
    protected double diagonal1;
    protected double diagonal2;

    protected void calculateLengths() {
        double temp;
        double tempX;
        double tempY;
        for(int i = 0; i < 3; ++i) {
            temp = Math.sqrt((double)((this.xVertex[i + 1] - this.xVertex[i]) * (this.xVertex[i + 1] - this.xVertex[i]) + (this.yVertex[i + 1] - this.yVertex[i]) * (this.yVertex[i + 1] - this.yVertex[i])));
            temp = (double)Math.round(temp * 100.0) / 100.0;
            this.lengths[i] = temp;
            tempX = (double)(this.xVertex[i + 1] + this.xVertex[i]) / 2.0;
            tempY = (double)(this.yVertex[i + 1] + this.yVertex[i]) / 2.0;
            this.MidPointsX[i] = (int)tempX;
            this.MidPointsY[i] = (int)tempY;
        }

        temp = Math.sqrt((double)((this.xVertex[0] - this.xVertex[3]) * (this.xVertex[0] - this.xVertex[3]) + (this.yVertex[0] - this.yVertex[3]) * (this.yVertex[0] - this.yVertex[3])));
        temp = (double)Math.round(temp * 100.0) / 100.0;
        this.lengths[3] = temp;
        tempX = (double)(this.xVertex[0] + this.xVertex[3]) / 2.0;
        tempY = (double)(this.yVertex[0] + this.yVertex[3]) / 2.0;
        this.MidPointsX[3] = (int)tempX;
        this.MidPointsY[3] = (int)tempY;
        this.diagonal1 = Math.sqrt((double)((this.xVertex[1] - this.xVertex[3]) * (this.xVertex[1] - this.xVertex[3]) + (this.yVertex[1] - this.yVertex[3]) * (this.yVertex[1] - this.yVertex[3])));
        this.diagonal2 = Math.sqrt((double)((this.xVertex[0] - this.xVertex[2]) * (this.xVertex[0] - this.xVertex[2]) + (this.yVertex[0] - this.yVertex[2]) * (this.yVertex[0] - this.yVertex[2])));
    }

    public double getLength(int i) {
        return this.lengths[i];
    }

    public double getD1() {
        return this.diagonal1;
    }

    public double getXMid(int i) {
        return (double)this.MidPointsX[i];
    }

    public double getYMid(int i) {
        return (double)this.MidPointsY[i];
    }
}
