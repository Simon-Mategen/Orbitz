package Controller.Calculators;

public class PositionCalculator {
    private double d;

    private double N;
    private double i;
    private double w;
    private double a;
    private double e;
    private double M;

    private double E0, E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16;
    private double x;
    private double y;

    private double r;
    private double v;

    private double xeclip;
    private double yeclip;



    public double calculateDay(double year, double month, double day){
        d = 367*year-(7*(year+((month+9)/12)))/4+((275+month)/9)+day-730530;
        return d;
    }

    public void setValues(double year, double month, double day){
        //De avrundar i denna formeln i deras uträkning och får d till -3543, utan
        //avrundningar får man -3634 ungefär. Använder man -3634 i de andra uträkningarna
        //får man samma svar på alla förutom för M, där bytte jag tillbaka till -3543 för att testa.
        d = 367*year-(7*(year+((month+9)/12)))/4 +((275+month)/9)+day-730530;

        //Mercury värden
        N = 48.3313 + (3.24587E-5 * d);
        i = 7.0047 + (5.00E-8 * d);
        w = 29.1241 + (1.01444E-5 * d);
        a = 0.387098;
        e = 0.205635 + (5.59E-10 * d);
        M = 168.6562 + (4.0923344368 * -3543) + 360*40;
        System.out.println("d = " + d + "\n" + "N = " +  N + "\n" + "i = " + i + "\n" + "w = " + w + "\n" + "a = " + a + "\n" + "e = " +  e + "\n" + "M = " +  M);

        //Mercury, E = the eccentric anomaly, FÅR INTE RÄTT VÄRDE PÅ E
        E0 = M + (180/Math.PI)*e*Math.sin(M)*(1+(e*Math.cos(M)));
        E1 = E0 - (E0-(180/Math.PI) * e * Math.sin(E0)-M)/(1-e*Math.cos(E0));
        E2 = E1 - (E1-(180/Math.PI) * e * Math.sin(E1)-M)/(1-e*Math.cos(E1));
        E3 = E2 - (E2-(180/Math.PI) * e * Math.sin(E2)-M)/(1-e*Math.cos(E2));
        E4 = E3 - (E3-(180/Math.PI) * e * Math.sin(E3)-M)/(1-e*Math.cos(E3));
        E5 = E4 - (E4-(180/Math.PI) * e * Math.sin(E4)-M)/(1-e*Math.cos(E4));
        E6 = E5 - (E5-(180/Math.PI) * e * Math.sin(E5)-M)/(1-e*Math.cos(E5));
        E7 = E6 - (E6-(180/Math.PI) * e * Math.sin(E6)-M)/(1-e*Math.cos(E6));
        E8 = E7 - (E7-(180/Math.PI) * e * Math.sin(E7)-M)/(1-e*Math.cos(E7));
        E9 = E8 - (E8-(180/Math.PI) * e * Math.sin(E8)-M)/(1-e*Math.cos(E8));
        E10 = E9 - (E9-(180/Math.PI) * e * Math.sin(E9)-M)/(1-e*Math.cos(E9));
        E11 = E10 - (E10 -(180/Math.PI) * e * Math.sin(E10)-M)/(1-e*Math.cos(E10));
        E12 = E11 - (E11-(180/Math.PI) * e * Math.sin(E11)-M)/(1-e*Math.cos(E11));
        E13 = E12 - (E12-(180/Math.PI) * e * Math.sin(E12)-M)/(1-e*Math.cos(E12));
        E14 = E13 - (E13-(180/Math.PI) * e * Math.sin(E13)-M)/(1-e*Math.cos(E13));
        E15 = E14 - (E14-(180/Math.PI) * e * Math.sin(E14)-M)/(1-e*Math.cos(E14));
        E16 = E15 - (E15-(180/Math.PI) * e * Math.sin(E15)-M)/(1-e*Math.cos(E15));
        System.out.println("E16 = " + E16);

        //Mercurys rectangular coordinates
        //x och y blir fel eftersom E är fel. Nu antar vi att E = 81.1572
        x = a * (Math.cos(81.1572)-e);
        y = a * (Math.sqrt(1-(e*e)))*Math.sin(81.1572);
        System.out.println("x = " + x);
        System.out.println("y = " + y);

        //Mercury, r = radius vector, v = true anomaly
        //Blir fel även med samma värde på E som på sidan
        //Enligt sidan ska det bli: r = 0.374862 (nära vår beräkning), v = 93.0727 (vår beräkning blir helt fel)
        r = Math.sqrt((x*x)+(y*y));
        v = Math.atan2(y, x);
        System.out.println("r = " + r);
        System.out.println("v = " + v);

        //Mercurys heliocentric ecliptic rectangular coordinates
        //Blir fel, sadface
        //Enligt sidan ska det bli: x = -0.367821, y = +0.061084
        xeclip = r * (Math.cos(N)*Math.cos(v+w)-Math.sin(N)*Math.sin(v+w)*Math.cos(i));
        yeclip = r * (Math.sin(N)*Math.cos(v+w)+Math.cos(N)*Math.sin(v+w)*Math.cos(i));
        System.out.println("xeclip = " + xeclip);
        System.out.println("yeclip = " + yeclip);
    }


}
