package model;

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
    private final double m = 0.000001;

    public MiddleNeuron(String name) {
        this.name = name;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getFi()
    {
        u =  Math.exp(-0.5*u*w);
        System.out.println("w = "+w +" u = " + u);
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getDelta() {

        delta = w*u*delta;
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public String getName() {
        return name;
    }

    public void correctWeight()
    {
        w = w - m*u*delta;
    }

    public String toString()
    {
        StringBuffer info = new StringBuffer();
        info.append("Name: "+name+"\n");
        info.append("W = " + w + "\n");
        System.out.println(info.toString());
        return info.toString();
    }


}
