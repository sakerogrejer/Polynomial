import java.util.ArrayList;
import java.util.LinkedList;

public class Main
{
    public static void main(String[] args)
    {
        Polynomial p = new Polynomial(new LinkedList<Term>());
        Polynomial p2 = new Polynomial(new LinkedList<Term>());
        p.polyInput();
        p2.polyInput();
        Polynomial p3 = p;
        p3.add(p2);
        p3.printPoly();
    }
}
