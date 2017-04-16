package model.functional;

import model.impl.RBFModelNeurons;

import java.util.ArrayList;

/**
 * Created by Ксю on 16.04.2017.
 */
public class MainFunctional
{
    public static double m = 0.01;

    public static final double MO = 10;
    public static final double D = 100;
    private static boolean isEnd = false;
    public static final double r = 3;

    public static ArrayList<RBFModelNeurons> correctCenter(ArrayList<RBFModelNeurons> network, double x)
    {
        isEnd = true;
        double min = network.get(0).getCenter();
        int numberNeuron = 0;
        for (int i=1; i<network.size(); i++) {
            double deltaC = x - network.get(i).getCenter();
            if(deltaC<0)
            {
                deltaC = (-1)*deltaC;
            }
            if (min > deltaC) {
                min = deltaC;
                numberNeuron = i;
                isEnd = false;
            }
        }
        network.get(numberNeuron).setCenter(min+m*(x-min));
        return network;
    }

    public static ArrayList<RBFModelNeurons> correctSigma(ArrayList<RBFModelNeurons> network, int countRootInputLayer, int countRootMiddleLayer)
    {
        ArrayList<RBFModelNeurons> model = new ArrayList<RBFModelNeurons>();

        double sigma = 0;
        double currentC = network.get(0).getCenter();
        for(int i=0; i<r; i++)
        {
            RBFModelNeurons n = (RBFModelNeurons) network.get(0).getParentNeuron(i);
            double c = n.getCenter();
            sigma = sigma+Math.pow(2,(currentC - c));
        }
        sigma = Math.sqrt(sigma/r);
        network.get(0).setSigma(sigma);
        //model.add(outLayer);

        int l=0;
        for(int count =1; count<countRootMiddleLayer+1; count++)
        {
            sigma = 0;
            currentC = network.get(count).getCenter();
            int countConnect = network.get(count).countConnectParentNeurons();



            for(int i=0; i<r; i++) {

                if(i+l<countConnect) {
                    RBFModelNeurons n = (RBFModelNeurons) network.get(count).getParentNeuron(i+l);
                    double c = n.getCenter();
                    sigma = sigma + Math.pow(2, (currentC - c));
                }
                else
                {
                    RBFModelNeurons n = (RBFModelNeurons) network.get(count).getNextNeuron(0);
                    double c = n.getCenter();
                    sigma = sigma+Math.pow(2,(currentC - c));
                }
            }
            sigma = Math.sqrt(sigma/r);
            network.get(count).setSigma(sigma);
            l++;
         //   model.add(neurons);

        }

        l=0;
        for(int count=countRootMiddleLayer+1; count<(countRootInputLayer+countRootMiddleLayer+1); count++)
        {
            sigma = 0;
            currentC = network.get(count).getCenter();
            int countConnect = network.get(count).countConnectNextNeurons();

            for(int i=0; i<r; i++) {

                if(i+l<countConnect) {
                    RBFModelNeurons n = (RBFModelNeurons) network.get(count).getNextNeuron(i+l);
                    double c = n.getCenter();
                    sigma = sigma + Math.pow(2, (currentC - c));
                }
                else {
                    RBFModelNeurons n = network.get(l-3);
                    double c = n.getCenter();
                    sigma = sigma+Math.pow(2,(currentC - c));
                }
                l++;
            }
            sigma = Math.sqrt(sigma/r);
            network.get(count).setSigma(sigma);
          //  model.add(neurons);
        }

        return network;
    }

    public static boolean isEnd()
    {
        return isEnd;
    }
}
