import java.util.*;
import java.util.function.Predicate;

public class Polynomial
{

    private ArrayList<Term> terms;
    Scanner scanner = new Scanner(System.in);

    //Create Polynomials
    public void polyInput()
    {
        System.out.println("Type q to quit");
        while(true)
        {
            System.out.println("Input as coef,exp: ");
            String input = scanner.next();
            if (input.equals("q"))
            {
                break;
            }
            else
            {
                String[] temp = input.split(",");
                terms.add(new Term(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            }
        }

        sortPoly();

    }

    //Adding Polynomials
    public static Polynomial add(Polynomial x, Polynomial y)
    {

        //Init vars
        Polynomial z = new Polynomial(new ArrayList<>());
        Polynomial leftx = x;
        Polynomial lefty = y;
        ArrayList<Term> added = new ArrayList<>();

        //Finding max polynomial
        char max = 'a';
        if(x.terms.size()>y.terms.size())
        {
            max = 'x';
        }else {max = 'y';}

        if(max == 'x')
        {
            for(int i = y.terms.size(); i < x.terms.size(); i++)
            {
                y.terms.add(new Term(0, 0));
            }
        }
        else
        {
            for(int i = x.terms.size(); i < y.terms.size(); i++)
            {
                y.terms.add(new Term(0, 0));
            }
        }

        //Add terms together and set copies to 0x^0 to add not like terms to final poly
        for(int k = 0; k < x.terms.size(); k++)
        {
            for(int n = k; n < x.terms.size(); n++)
            {
                if (x.terms.get(k).getExp() == y.terms.get(n).getExp())
                {
                    z.terms.add(new Term(x.terms.get(k).getCoef() + y.terms.get(k).getCoef(), x.terms.get(k).getExp()));
                    leftx.terms.set(k, new Term(0, 0));
                    lefty.terms.set(k, new Term(0, 0));
                }
            }
        }

        //Combine terms not added
        leftx.terms.addAll(lefty.terms);
        z.terms.addAll(leftx.terms);

        //Remove terms that are 0x^0
        z.terms.removeIf(Polynomial::isZeroZero);

        return z;

    }

    //Print Poly
    public void printPoly()
    {
        for(int i = 0; i < terms.size(); i++)
        {
            if(i != terms.size()-1)
            {
                System.out.print(terms.get(i).getCoef() + "x^" + terms.get(i).getExp() + " + ");
            }else
            {
                System.out.print(terms.get(i).getCoef() + "x^" + terms.get(i).getExp());
            }
        }
    }

    //Check for a null term
    private static boolean isZeroZero(Term t)
    {
        if(t.getCoef() == 0)
        {
            return true;
        }else{ return false; }
    }

    //Sort polynomial based on exponent
    public void sortPoly()
    {
       terms.sort(Comparator.comparing(Term::getExp).reversed());
    }

    public Polynomial(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

}
