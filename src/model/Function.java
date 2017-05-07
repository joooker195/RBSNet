package model;

import controll.MainFunctional;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ксю on 04.05.2017.
 */
public class Function {
    private ArrayList<Neuron> firstLayer;
    private ArrayList<Neuron> secondLayer;
    private final double m = 0.00001;
    int n = 10000;
    double delta = 1;
    int iter = 0;
    public static final double MO = 10;
    public static final double D = 100;

    public Function(ArrayList<ArrayList<Neuron>> network) {
        firstLayer = network.get(0);
        secondLayer = network.get(1);
    }

    public void learn(ArrayList<Double> data) {
        // data = sort();
        System.out.println("Learn");
        while (delta > 0.001) {
            delta = 0;
            int k = 0;
            for (int i = 0; i < firstLayer.size(); i++) {
                ArrayList<Double> a = new ArrayList<Double>();
                for (int j = 0; j < 3; j++) {
                    a.add(Mathemath.normalize(data.get(k), MO, D));
                    k++;
                }
                InputNeuron n = (InputNeuron) firstLayer.get(i);
                double u = Mathemath.normalize(n.getU(a));
                MiddleNeuron mn = (MiddleNeuron) secondLayer.get(i);
                mn.setU(u);
                delta += mn.getFi();
                System.out.println("delta = " + delta);
                secondLayer.set(i, mn);
            }
            System.out.println(Mathemath.normalize(data.get(k)));
            delta -= Mathemath.normalize(data.get(k));
            delta = Mathemath.funActivator(delta);


            if (MainFunctional.condition(delta, Mathemath.normalize(data.get(k)))) {
                break;
            }

            System.out.println(delta);
            k++;
            iter++;

            for (int i = 0; i < secondLayer.size(); i++) {
                ArrayList<Double> a = new ArrayList<Double>();
                for (int j = 0; j < 3; j++) {
                    a.add(data.get(k));
                    k++;
                }
                MiddleNeuron mn = (MiddleNeuron) secondLayer.get(i);
                InputNeuron n = (InputNeuron) firstLayer.get(i);
                mn.setDelta(delta);
                mn.correctWeight();
                n.corrcetCenters(mn.getDelta(), m, a);
                n.correctSigmas(mn.getDelta(), m, a);

                //     n.toString();

                //  mn.toString();

                secondLayer.set(i, mn);
                firstLayer.set(i, n);

            }


        }

        for (int i = 0; i < firstLayer.size(); i++) {
            //   firstLayer.get(i).toString();
            //   secondLayer.get(i).toString();
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


}