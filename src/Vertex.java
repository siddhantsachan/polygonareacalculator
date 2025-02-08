//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Vertex {
    protected int[] xVertex;
    protected int[] yVertex;
    protected double area = 100.0;


    protected void calculateArea() {
        this.area = 0.0;

        for(int i = 0; i < 3; ++i) {
            this.area += (double)(this.xVertex[i] * this.yVertex[i + 1] - this.xVertex[i + 1] * this.yVertex[i]);
        }

        this.area += (double)(this.xVertex[3] * this.yVertex[0] - this.xVertex[0] * this.yVertex[3]);
        this.area = Math.abs(this.area) / 2.0;
    }

    public void setXs(int[] i) {
        this.xVertex     = i;
    }

    public void setYs(int[] i) {
        this.yVertex = i;
    }

    public int getX(int i) {
        return this.xVertex[i];
    }

    public int getY(int i) {
        return this.yVertex[i];
    }

    public int[] getXs() {
        return this.xVertex;
    }

    public int[] getYs() {
        return this.yVertex;
    }

    public double getArea() {
        return this.area;
    }
}
