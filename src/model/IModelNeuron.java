package model;

/**
 * Created by Ксю on 15.04.2017.
 */
public interface IModelNeuron
{
    String getStatus();

    void setStatus(String status);

    double getSignal();

    void setSignal(double x);

    double getWeight();

    void setWeight(double w);

    double getValueNode();

    void setValueNode(double y);

    double getCountNeurons();

    void setCountNeurons(double countNeurons);

    IModelNeuron getNextNeuron(int number);

    void setNextNeuron(IModelNeuron neuron);

    IModelNeuron getParentNeuron(int number);

    void setParentNeuron(IModelNeuron neuron);

    int countConnectNextNeurons();

    int countConnectParentNeurons();


}
