import model.impl.RBFModelNetwork;
import model2.ModelNetwork;
import model2.Neuron;

import java.util.ArrayList;

/**
 * Created by Ксю on 16.04.2017.
 */
public class Main
{
    public static void main(String[] args)
    {
       // RBFModelNetwork model = new RBFModelNetwork(4, 5);
        ModelNetwork model = new ModelNetwork(4, 3);
        ArrayList<ArrayList<Neuron>> network =  model.createNetwork();


    }
}
