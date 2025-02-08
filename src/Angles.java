public class Angles extends Length {
    double[] angles = new double[]{90.0, 90.0, 90.0, 90.0};
    boolean[] crossProducts = new boolean[]{true, true, true, true};

    private void calculateAngles() {
        int trueFrequency = 0;
        int falseFrequency = 0;
        double a0 = Math.acos((this.lengths[0] * this.lengths[0] + this.lengths[3] * this.lengths[3] - this.diagonal1 * this.diagonal1) / (2.0 * this.lengths[0] * this.lengths[3]));
        a0 = a0 / Math.PI * 180.0;
        a0 = (double)Math.round(a0 * 100.0) / 100.0;
        double a1 = Math.acos((this.lengths[0] * this.lengths[0] + this.lengths[1] * this.lengths[1] - this.diagonal2 * this.diagonal2) / (2.0 * this.lengths[0] * this.lengths[1]));
        a1 = a1 / Math.PI * 180.0;
        a1 = (double)Math.round(a1 * 100.0) / 100.0;
        double a2 = Math.acos((this.lengths[1] * this.lengths[1] + this.lengths[2] * this.lengths[2] - this.diagonal1 * this.diagonal1) / (2.0 * this.lengths[2] * this.lengths[1]));
        a2 = a2 / Math.PI * 180.0;
        a2 = (double)Math.round(a2 * 100.0) / 100.0;
        double a3 = Math.acos((this.lengths[3] * this.lengths[3] + this.lengths[2] * this.lengths[2] - this.diagonal2 * this.diagonal2) / (2.0 * this.lengths[3] * this.lengths[2]));
        a3 = a3 / Math.PI * 180.0;
        a3 = (double)Math.round(a3 * 100.0) / 100.0;
        this.angles[0] = a0;
        this.angles[1] = a1;
        this.angles[2] = a2;
        this.angles[3] = a3;

        int i;
        for(i = 1; i < 3; ++i) {
            this.crossProducts[i] = (this.xVertex[i + 1] - this.xVertex[i]) * (this.yVertex[i] - this.yVertex[i - 1]) > (this.xVertex[i] - this.xVertex[i - 1]) * (this.yVertex[i + 1] - this.yVertex[i]);
        }

        this.crossProducts[0] = (this.xVertex[1] - this.xVertex[0]) * (this.yVertex[0] - this.yVertex[3]) > (this.xVertex[0] - this.xVertex[3]) * (this.yVertex[1] - this.yVertex[0]);
        this.crossProducts[3] = (this.xVertex[0] - this.xVertex[3]) * (this.yVertex[3] - this.yVertex[2]) > (this.xVertex[3] - this.xVertex[2]) * (this.yVertex[0] - this.yVertex[3]);

        for(i = 0; i < 4; ++i) {
            if (this.crossProducts[i]) {
                ++trueFrequency;
            } else {
                ++falseFrequency;
            }
        }

        if (falseFrequency > trueFrequency) {
            for(i = 0; i < 4; ++i) {
                this.crossProducts[i] = !this.crossProducts[i];
            }
        }

        for(i = 0; i < 4; ++i) {
            if (!this.crossProducts[i]) {
                this.angles[i] = 360.0 - this.angles[i];
            }
        }
    }

    public double getAngle(int i) {
        return this.angles[i];
    }

    public boolean getCrossProduct(int i) {
        return this.crossProducts[i];
    }

    public void changePoint(int x, int y, int i) {
        this.xVertex[i] = x;
        this.yVertex[i] = y;
        this.calculateArea();
        this.calculateLengths();
        this.calculateAngles();
    }

    public void changePoint() {
        this.calculateArea();
        this.calculateLengths();
        this.calculateAngles();
    }
}
