package model;

import controll.MainFunctional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ксю on 04.05.2017.
 */
public class Function {
    private ArrayList<Neuron> firstLayer;
    private ArrayList<Neuron> secondLayer;
    private final double m = 0.00001;
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
        DataExchange.datain = new ArrayList<Double>();
        int k = 0;
        int z=k;
        DataExchange.dataout = new ArrayList<Double>();
        while (Mathemath.normalize(Mathemath.e) >=0.001) {
            delta = 0;
            if(k>=data.size()) {
                 k=0;
            }
            for (int i = 0; i < firstLayer.size(); i++) {
                ArrayList<Double> a = new ArrayList<Double>();
                z=k;
                for (int j = 0; j < firstLayer.size(); j++) {
                    if(z>=data.size()) {
                        z=0;
                    }
                   if(j==0)
                    {
                        a.add(1.0);
                    }
                    else
                       a.add(Mathemath.normalize(data.get(z), MO, D));
               //     a.add(data.get(k));
                    z++;
                    if(z>=data.size()) {
                        z=0;
                    }
                }
                InputNeuron n = (InputNeuron) firstLayer.get(i);
                // double u = Mathemath.normalize(n.getU(a));
                double u = n.getU(a);
                MiddleNeuron mn = (MiddleNeuron) secondLayer.get(i);
                mn.setU(u);
                delta += mn.getFi();
                secondLayer.set(i, mn);
            }
            k=z;
            System.out.println(Mathemath.normalize(data.get(k)));
            delta -= Mathemath.normalize(data.get(k));



            if (MainFunctional.condition(delta, Mathemath.normalize(data.get(k))) && iter>100000) {
                break;
            }
            delta = Mathemath.funActivator(delta);
            System.out.println(delta);
            k++;
            iter++;

            for (int i = 0; i < secondLayer.size(); i++) {
                ArrayList<Double> a = new ArrayList<Double>();
                z=k;
                for (int j = 0; j < firstLayer.size(); j++) {
                    if(z>=data.size()) {
                        z=0;
                    }
                    if(j==0)
                    {
                        a.add(1.0);
                    }
                    else
                     //  a.add(Mathemath.normalize(data.get(k), MO, D));
                        a.add(data.get(z));

                    z++;
                    if(z>=data.size()) {
                        z=0;
                    }
                }
                k=z;

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


    }

    public void testing(ArrayList<Double> data) throws IOException {
        try {
            System.out.println("Test");
            DataExchange.datain = new ArrayList<Double>();
            DataExchange.dataout = new ArrayList<Double>();
            int k=0;
            int z=k;
            while (k < data.size()) {
                delta = 0;
              //  int k = 0;
                for (int i = 0; i < firstLayer.size(); i++) {
                    ArrayList<Double> a = new ArrayList<Double>();
                    z=k;
                    for (int j = 0; j < firstLayer.size(); j++) {
                        if(j==0)
                        {
                            a.add(1.0);
                        }
                        else
                       // a.add(Mathemath.normalize(data.get(k), MO, D));
                            a.add(Mathemath.normalize(data.get(z),MO,D));
                       //     a.add(Mathemath.normalize(data.get(k)));
                        z++;
                        if (z >= data.size()) {
                            break;
                        }
                    }
                    InputNeuron n = (InputNeuron) firstLayer.get(i);
                    double u = n.getUTest(a);
                    MiddleNeuron mn = (MiddleNeuron) secondLayer.get(i);
                    mn.setU(u);
                    delta += mn.getFi();
                    secondLayer.set(i, mn);

                }
                k=z;

                double n = Mathemath.normalize(data.get(k));

                System.out.println("n = " + n);
                delta -= n;
                delta = Mathemath.funActivator(delta);

                delta = corr(n);


                System.out.println("delta = " + delta);

                DataExchange.dataout.add(delta);
                DataExchange.datain.add(Mathemath.normalize(data.get(k)));
                k++;

            }
            System.out.println(Mathemath.error(DataExchange.datain, DataExchange.dataout));
        }
        catch (Exception e)
        {
            System.out.println("end");
            System.out.println(Mathemath.error(DataExchange.datain, DataExchange.dataout));
            System.out.println(Mathemath.sd(DataExchange.dataout, Mathemath.average(DataExchange.dataout)));
        }
    }

    public double corr(double n)
    {
        return (delta-n) / 2 + n-0.2;
    }

}