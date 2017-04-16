package model;

import model.impl.RBFModelNeurons;

import java.util.ArrayList;

/**
 * Created by Ксю on 15.04.2017.
 */
public interface IModelNetwork
{
    ArrayList<RBFModelNeurons> createNetwork();
}
