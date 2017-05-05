package model2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ксю on 04.05.2017.
 */
public class Function
{
    private ArrayList<Neuron> firstLayer;
    private ArrayList<Neuron> secondLayer;
    private final double m = 0.01;
    int n = 3;

    public Function(ArrayList<ArrayList<Neuron>> network)
    {
        firstLayer = network.get(0);
        secondLayer = network.get(1);
    }

    public void learn(ArrayList<Double> data)
    {
       ArrayList a = sort();
       for(int i=0; i< firstLayer.size(); i++)
       {
           InputNeuron n = (InputNeuron) firstLayer.get(i);
           double u = n.getU(a);
           MiddleNeuron mn = (MiddleNeuron) secondLayer.get(i);
           mn.setU(u);
       }

    }

    private static double initGaussianRandomWeight() {
        Random r = new Random();
        double m = 0;
        double sqrS = 1;
        double powE = -(Math.pow(r.nextGaussian() - m, 2)) / (2 * sqrS);
        double coff = 1 / (Math.sqrt(2 * sqrS * Math.PI));
        return Math.pow(Math.E, powE) * coff;
    }



    private ArrayList<Double> sort()
    {
        double[] a = new double[n];
        for(int i=0; i< n;i++)
        {
            a[i] = initGaussianRandomWeight();
        }

        for (int i = 0; i < n; i++) {

            for (int j = n - 1; j > i; j--) {

                if (a[j] < a[j - 1])
                {
                    double temp = a[j-1];
                    a[j - 1] = a[j];
                    a[ j ] = temp;
                }
            }
        }

        ArrayList<Double> ret = new ArrayList<Double>();
        for(int i=0; i<n; i++)
        {
            ret.add(a[i]);
        }

        return ret;
    }
}
