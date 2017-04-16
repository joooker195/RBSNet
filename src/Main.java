import model.impl.RBFModelNetwork;

/**
 * Created by Ксю on 16.04.2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        RBFModelNetwork model = new RBFModelNetwork(4, 5);
        model.createNetwork();

    }
}
