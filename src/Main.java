import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Polynomial p = new Polynomial(new ArrayList<>());
        Polynomial p2 = new Polynomial(new ArrayList<>());
        p.polyInput();
        p2.polyInput();
        Polynomial p3 = Polynomial.add(p, p2);
        p3.printPoly();
    }
}
