package Model;

import java.util.LinkedList;

public class Solarsystem implements ISolarsystem
{
    LinkedList<Orbit> orbits = new LinkedList<>();

    @Override
    public void addOrbit(Orbit orbit)
    {
        orbits.add(orbit);
    }

    public Orbit getOrbit(int index)
    {
        return orbits.get(index);
    }


}
