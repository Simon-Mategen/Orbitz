package Controller;

import Controller.Calculators.OrbitCalculator;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;

    public Controller()
    {
        reader = new APIReader();
    }

}
