import model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ксю on 16.04.2017.
 */
public class Main
{
    public static void main(String[] args) throws IOException {
        ModelNetwork model = new ModelNetwork(3,3);//6,5
        ArrayList<ArrayList<Neuron>> network = model.createNetwork();
        Function f = new Function(network);
        ArrayList<Double> data = DataExchange.readFromExcel("dataBrent.xls", "Лист1");
        f.learn(data);
      //  DataExchange.writeToExcel("1.xlsx");

        ArrayList<Double> datatest = DataExchange.readFromExcel("dataTestBrent.xls", "Лист1");
        f.testing(datatest);
        DataExchange.writeToExcel("3.xlsx");

        //6:0.02849;01168
        //5:0.02828;0.01159
        //4:0.02569;0.01140
        //3:0.01644;0.01064
    }



}
