package model.impl;

import model.IModelNeuron;

import java.util.ArrayList;

/**
 * Created by Ксю on 15.04.2017.
 */
public class RBFModelNeurons implements IModelNeuron {


    private String status = null; //отслживание положение нейрона в сети
    private double x = 0;//входящий сигнал
    private double w = 0;//вес
    private double y = 0;//фактическое значение в узле
    private double c=0;
    private double sigma = 0;
    private double countNeurons = 0;//количество слоев
    private ArrayList<IModelNeuron> nextNeuron;
    private ArrayList<IModelNeuron> parentNeuron;

    public RBFModelNeurons()
    {
        nextNeuron = new ArrayList<IModelNeuron>();
        parentNeuron = new ArrayList<IModelNeuron>();
    }

    public RBFModelNeurons(ArrayList<IModelNeuron> nextNeuron)
    {
        this.nextNeuron = nextNeuron;
    }


    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public double getSignal() {
        return x;
    }

    @Override
    public void setSignal(double x) {
        this.x = x;
    }

    @Override
    public double getWeight() {
        return w;
    }

    @Override
    public void setWeight(double w) {
        this.w = w;
    }

    @Override
    public double getValueNode() {
        return y;
    }

    @Override
    public void setValueNode(double y) {
        this.y = y;
    }

    @Override
    public double getCountNeurons() {
        return countNeurons;
    }

    @Override
    public void setCountNeurons(double countNeurons) {
        this.countNeurons = countNeurons;
    }

    @Override
    public IModelNeuron getNextNeuron(int number) {
        return nextNeuron.get(number);
    }

    @Override
    public void setNextNeuron(IModelNeuron neuron) {

        nextNeuron.add(neuron);
    }

    @Override
    public IModelNeuron getParentNeuron(int number) {
        return parentNeuron.get(number);
    }

    @Override
    public void setParentNeuron(IModelNeuron neuron) {
        parentNeuron.add(neuron);
    }

    @Override
    public int countConnectNextNeurons() {
        return nextNeuron.size();
    }

    @Override
    public int countConnectParentNeurons() {
        return parentNeuron.size();
    }

    public double getCenter() {
        return c;
    }

    public void setCenter(double c) {
        this.c = c;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public ArrayList<IModelNeuron> getAllLinks()
    {
        return nextNeuron;
    }





    public String toString()
    {
        StringBuffer info = new StringBuffer();
        info.append("\nStatus: "+status+"\n").append("Connects:\n");
       /* for(int i=0; i<nextNeuron.size(); i++)
        {
            if(nextNeuron.get(i)!=null) {
                info.append("Con: " + i).append(" next: " + nextNeuron.get(i).getStatus() + "\n");
            }
            else
            {
                info.append("Con: " + i).append(" next: null\n");
            }

        }*/
        info.append("cen: "+c).append(" sigma: "+sigma);
        return info.toString();
    }
}
