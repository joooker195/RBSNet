package model2;

import java.util.ArrayList;

/**
 * Created by Ксю on 04.05.2017.
 */
public class InputNeuron implements Neuron
{
    private ArrayList<Double> c = new ArrayList<Double>();
    private ArrayList<Double> sigma = new ArrayList<Double>();
    private ArrayList<Double> x = new ArrayList<Double>();
    private String name;

    public InputNeuron(String name) {
        this.name = name;
    }

    public ArrayList<Double> getCenters() {
        return c;
    }

    public void setCenters(ArrayList<Double> c) {
        this.c = c;
    }

    public ArrayList<Double> getSigmas() {
        return sigma;
    }

    public void setSigmas(ArrayList<Double> sigma) {
        this.sigma = sigma;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public void setX(ArrayList<Double> x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void addSigma(double s)
    {
        sigma.add(s);
    }

    public double getSigma(int number)
    {
        return sigma.get(number);
    }

    public void addCenter(double s)
    {
        c.add(s);
    }

    public void setCenter(int number, double s)
    {
        c.set(number, s);
    }

    public double getCenter(int number)
    {
        return c.get(number);
    }


    public void correct(ArrayList<Double> x)
    {
        this.x = x;
        double m = 0.01;
        double min = x.get(0) - getCenter(0);
        int numberNeuron = 0;
        if(min<0)
        {
            min = (-1)*min;
        }
        for (int i=1; i<c.size(); i++) {
            double deltaC = x.get(i) - getCenter(i);
            if(deltaC<0)
            {
                deltaC = (-1)*deltaC;
            }
            if (min > deltaC) {
                min = deltaC;
                numberNeuron = i;
            }
        }
        setCenter(numberNeuron, min+m*(x.get(numberNeuron)-min));
    }


    public void searchSigma()
    {
        double a = c.get(0) - c.get(1);
        double b = c.get(0) - c.get(2);
        double r = (Math.pow(2, a) + Math.pow(2, b))/3;
        sigma.add(r);

        a = c.get(1) - c.get(0);
        b = c.get(1) - c.get(2);
        r = (Math.pow(2, a) + Math.pow(2, b))/3;
        sigma.add(r);

        a = c.get(2) - c.get(0);
        b = c.get(2) - c.get(1);
        r = (Math.pow(2, a) + Math.pow(2, b))/3;
        sigma.add(r);

    }

    public String toString()
    {
        StringBuffer info = new StringBuffer();
        info.append("Name: "+name+"\n");
        info.append("Centers:\n");
        for(int i=0; i<c.size(); i++)
        {
            info.append("c"+String.valueOf(i)+" = " + c.get(i)+"\n");
        }
        info.append("Sigmas:\n");
        for(int i=0; i<sigma.size(); i++)
        {
            info.append("sigma"+String.valueOf(i)+" = " + sigma.get(i)+"\n");
        }
        System.out.println(info.toString());
        return info.toString();
    }

    public double getU(ArrayList<Double> x)
    {
        double u=0;
        for (int i=0; i<x.size(); i++)
        {
            u+=Math.pow(2, x.get(i)-c.get(i))/Math.pow(2, sigma.get(i));
        }

        return  u;

    }
}
