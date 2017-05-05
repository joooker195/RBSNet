package model2;

import java.util.ArrayList;

/**
 * Created by Ксю on 04.05.2017.
 */
public class MiddleNeuron implements Neuron
{
    private double w;
    private String name;
    private ArrayList<Double> input = new ArrayList<Double>();
    private double u;
    private double delta;

    public MiddleNeuron(String name) {
        this.name = name;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public String getName() {
        return name;
    }
}
