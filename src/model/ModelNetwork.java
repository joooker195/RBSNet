package model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ксю on 04.05.2017.
 */
public class ModelNetwork {

    private int countRootInputLayer; //количество нейронов во входном слое
    private int n;//количество центров
    private ArrayList<Neuron> firstLayer; //первый слой
    private ArrayList<Neuron> secondLayer; //второй слой
    private ArrayList<ArrayList<Neuron>> network = new ArrayList<ArrayList<Neuron>>();


    public ModelNetwork(int countRootInputLayer, int n) {
        this.countRootInputLayer = countRootInputLayer;
        this.n = n;
        firstLayer = new ArrayList<Neuron>();
        secondLayer = new ArrayList<Neuron>();
    }

    public ArrayList<ArrayList<Neuron>> createNetwork() throws IOException {

        //инициализация сети
        for(int i=0; i<countRootInputLayer; i++)
        {
            InputNeuron neuron = new InputNeuron("0"+String.valueOf(i));
            for(int j=0; j<n;j++)
            {
                neuron.addCenter(initGaussianRandomWeight());
            }
        //    neuron.toString();
            firstLayer.add(neuron);

            MiddleNeuron mneuron = new MiddleNeuron("1"+String.valueOf(i));
            mneuron.setW(initGaussianRandomWeight());
            secondLayer.add(mneuron);
        }


        ArrayList<Double> data = DataExchange.readFromExcel("dataBrent.xls", "Лист1");
        //коррекция центров
        for (int i=0; i < 10000; i++)
        {
            ArrayList<Double> a = new ArrayList<Double>();
            for(int k=0; k<5; k++) {
                a.add(data.get(k));
            }
            for(int j=0; j<firstLayer.size(); j++)
            {
                InputNeuron n = (InputNeuron) firstLayer.get(j);
                n.correct(a);
                firstLayer.set(j, n);
            }
        }
        for(int j=0; j<firstLayer.size(); j++)
        {
            InputNeuron n = (InputNeuron) firstLayer.get(j);
            n.searchSigma();
            n.toString();
            firstLayer.set(j,n);
        }

        network.add(firstLayer);
        network.add(secondLayer);

        return network;
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
