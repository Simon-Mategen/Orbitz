package Model;

import java.util.LinkedList;

public interface ISolarsystem
{
    LinkedList<Orbit> orbits = null;
    Sun sun = null;

    public void addOrbit(Orbit orbit);




}
