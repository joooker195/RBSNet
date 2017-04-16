package model.impl;

import model.IModelNetwork;
import model.IModelNeuron;
import model.functional.MainFunctional;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ксю on 15.04.2017.
 */
public class RBFModelNetwork implements IModelNetwork {
    private int countRootInputLayer; //количество нейронов во входном слое
    private int countRootMiddleLayer;//количсетво нейроной в промежуточном слое
    private ArrayList<IModelNeuron> firstLayer; //первый слой
    private ArrayList<IModelNeuron> secondLayer; //второй слой
    private ArrayList<RBFModelNeurons> neurons;
    private RBFModelNeurons outLayer; //выходной нейрон

    public RBFModelNetwork(int countRootInputLayer, int countRootMiddleLayer) {
        this.countRootInputLayer = countRootInputLayer;
        this.countRootMiddleLayer = countRootMiddleLayer;
        firstLayer = new ArrayList<IModelNeuron>();
        secondLayer = new ArrayList<IModelNeuron>();
        neurons = new ArrayList<RBFModelNeurons>();
    }


    @Override
    public ArrayList<RBFModelNeurons> createNetwork() {

        //.................................outLayer
        outLayer = new RBFModelNeurons();
        outLayer.setStatus("out");
        outLayer.setWeight(initGaussianRandomWeight());
        outLayer.setNextNeuron(null);
        outLayer.setCenter(initGaussianRandomWeight());
        neurons.add(outLayer);
        //....................................middleLayer
        for (int i = 0; i < countRootMiddleLayer; i++) {
            RBFModelNeurons neuron = new RBFModelNeurons();
            neuron.setStatus("1" + String.valueOf(i));
            neuron.setWeight(initGaussianRandomWeight());
            neuron.setCenter(initGaussianRandomWeight());
            neuron.setNextNeuron(outLayer);
            outLayer.setParentNeuron(neuron);
            secondLayer.add(neuron);
            neurons.add(neuron);
        }
        //.....................................inputLayer
        for (int i = 0; i < countRootInputLayer; i++) {
            RBFModelNeurons neuron = new RBFModelNeurons();
            neuron.setStatus("0" + String.valueOf(i));
            neuron.setWeight(initGaussianRandomWeight());
            neuron.setCenter(initGaussianRandomWeight());
            for (IModelNeuron n : secondLayer) {
                neuron.setNextNeuron(n);
                n.setParentNeuron(neuron);
            }
            firstLayer.add(neuron);
            neurons.add(neuron);
        }



        double[] a = sort();

        System.out.println("До!!!!!!!!!!!!" + toString());

        int i=0;
        for (; i < 10000; i++) {
            if(MainFunctional.isEnd())
            {
                break;
            }
            neurons = MainFunctional.correctCenter(neurons, a[i]);
        }
        neurons = MainFunctional.correctSigma(neurons, countRootInputLayer,countRootMiddleLayer);

        System.out.println(i);
        System.out.println("\nПосле!!!!!!! " + toString());
        return neurons;
    }

    private static double initGaussianRandomWeight() {
        Random r = new Random();
        double m = 0;
        double sqrS = 1;
        double powE = -(Math.pow(r.nextGaussian() - m, 2)) / (2 * sqrS);
        double coff = 1 / (Math.sqrt(2 * sqrS * Math.PI));
        return Math.pow(Math.E, powE) * coff;
    }


    public String toString() {
        StringBuffer info = new StringBuffer();
        info.append("Model network:\n");

        for (IModelNeuron n : neurons) {
            info.append(n.toString());
        }

        return info.toString();
    }

    private double[] sort()
    {
        double[] a = new double[10000];
        for(int i=0; i< 10000;i++)
        {
            a[i] = initGaussianRandomWeight();
        }

        for (int i = 0; i < 10000; i++) {

            for (int j = 10000 - 1; j > i; j--) {

                if (a[j] < a[j - 1])
                {
                    double temp = a[j-1];
                    a[j - 1] = a[j];
                    a[ j ] = temp;
                }
            }
        }

        return a;
    }
}
