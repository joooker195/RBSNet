import model.DataExchange;
import model.Function;
import model.ModelNetwork;
import model.Neuron;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ксю on 16.04.2017.
 */
public class Main
{
    static int n = 1000000;
    public static void main(String[] args) throws IOException {
        // RBFModelNetwork model = new RBFModelNetwork(4, 5);
        ModelNetwork model = new ModelNetwork(7, 5);
        ArrayList<ArrayList<Neuron>> network = model.createNetwork();
        Function f = new Function(network);
        ArrayList<Double> data = DataExchange.readFromExcel("dataBrent.xls", "Лист1");
        f.learn(data);
        ArrayList<Double> datatest = DataExchange.readFromExcel("dataTestBrent.xls", "Лист1");
        f.testing(datatest);
       DataExchange.writeToExcel("2.xlsx");
    }



}
