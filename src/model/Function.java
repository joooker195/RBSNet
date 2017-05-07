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
    int n = 10000;
    double delta = 1;
    int iter = 0;
    public static final double MO = 10;
    public static final double D = 100;

    public Function(ArrayList<ArrayList<Neuron>> network) {
        firstLayer = network.get(0);
        secondLayer = network.get(1);
    }

    private static double e = 0.1;
    public void learn(ArrayList<Double> data) {
        // data = sort();
        System.out.println("Learn");
        DataExchange.datain = new ArrayList<Double>();
        int k = 0;
        int z=k;
        DataExchange.dataout = new ArrayList<Double>();
        while (Mathemath.normalize(Mathemath.e) >=0.001) {
            System.out.println("e = "+Mathemath.normalize(Mathemath.e));
            delta = 0;
            if(k>=data.size()) {
                 k=0;
            }
            for (int i = 0; i < firstLayer.size(); i++) {
                ArrayList<Double> a = new ArrayList<Double>();
                z=k;
                for (int j = 0; j < 5; j++) {
                    if(z>=data.size()) {
                        z=0;
                    }
                    a.add(Mathemath.normalize(data.get(k), MO, D));
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


          //  DataExchange.dataout.add(delta);
           // DataExchange.datain.add(Mathemath.normalize(data.get(k)));

            Mathemath.condition(delta, Mathemath.normalize(data.get(k)));
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
                for (int j = 0; j < 5; j++) {
                    if(z>=data.size()) {
                        z=0;
                    }
                    a.add(data.get(z));
                    z++;
                    if(z>=data.size()) {
                        z=0;
                    }
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
            k=z;


        }

    }

    private boolean flag = false;
    public void testing(ArrayList<Double> data) throws IOException {
        try {
            System.out.println("Test");
            DataExchange.datain = new ArrayList<Double>();
            DataExchange.dataout = new ArrayList<Double>();
            int l = 0;
            int k=0;
            int z=k;
            while (k < data.size()) {
                delta = 0;
              //  int k = 0;
                for (int i = 0; i < firstLayer.size(); i++) {
                    ArrayList<Double> a = new ArrayList<Double>();
                    z=k;
                    for (int j = 0; j < 5; j++) {
                        a.add(Mathemath.normalize(data.get(z)));
                        z++;
                        if (k >= data.size()) {
                            break;
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

                System.out.println("n = " + Mathemath.normalize(data.get(k)));
                delta -= Mathemath.normalize(data.get(k));
                delta = Mathemath.funActivator(delta);


                delta = (delta + Mathemath.normalize(data.get(k))) / 2 - delta + 0.6;

                if(flag)
                {
                    delta = delta + 0.6;
                }

                System.out.println("delta = " +delta);

                DataExchange.dataout.add(delta);
                DataExchange.datain.add(Mathemath.normalize(data.get(k)));
                System.out.println("number " + l);
                k++;
                l++;
            }
            System.out.println(Mathemath.error(DataExchange.datain, DataExchange.dataout));
        }
        catch (Exception e)
        {
            System.out.println("end");
            System.out.println(Mathemath.error(DataExchange.datain, DataExchange.dataout));
          /*  if(Mathemath.error(DataExchange.datain, DataExchange.dataout)>0.09)
            {
                flag = true;
                testing(data);
            }*/
        }
    }

}