import java.util.*;

public class Polynomial implements Cloneable
{

    protected ArrayList<Term> terms;
    Scanner scanner = new Scanner(System.in);

    //Constructors
    public Polynomial(ArrayList<Term> terms) {
        this.terms = terms;
        sortPoly();
    }

    public Polynomial()
    {
        this.terms = new ArrayList<>();
        sortPoly();
    }

    public Polynomial(Polynomial original) {
        this.terms = new ArrayList<>();
        for(Term t : original.terms)
        {
            this.terms.add(new Term(t.getCoefficient(), t.getExponent()));
        }
    }

    //Functions
    @Override
    public String toString()
    {

        sortPoly();

        if(terms.size() == 0)
        {
            return "0";
        }

        StringBuilder s = new StringBuilder();

        for(int i = 0; i < terms.size(); i++)
        {
            if(i == 0 && terms.get(i).getCoefficient() >= 0)
            {
                s.append(terms.get(i) + " ").replace(0,1,"");
            }
            else
            {
                s.append(terms.get(i) + " ");
            }

        }

        return s.toString();
    }

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
    public Polynomial add(Polynomial y) {
        this.sortPoly();
        y.sortPoly();
        Polynomial p = new Polynomial();
        Polynomial[] minmax = maxPoly(this, y);

        int min = Math.min(minmax[0].terms.size(), minmax[1].terms.size())-1;
        int max = Math.max(minmax[0].terms.size(), minmax[1].terms.size())-1;



        return p;

    }

    //Return Bigger Termed Polynomial
    public Polynomial[] maxPoly(Polynomial x, Polynomial y)
    {
        x.sortPoly(); y.sortPoly();
        if(x.terms.get(0).getExponent() > y.terms.get(0).getExponent())
        {
            return new Polynomial[]{x, y};
        }
        else
        {
            return new Polynomial[]{y, x};
        }
    }

    //Print Poly
    public void printPoly()
    {
        for(int i = 0; i < terms.size(); i++)
        {
            if(i != terms.size()-1)
            {
                System.out.print(terms.get(i).getCoefficient() + "x^" + terms.get(i).getExponent() + " + ");
            }else
            {
                System.out.print(terms.get(i).getCoefficient() + "x^" + terms.get(i).getExponent());
            }
        }
    }

    //Check for a null term
    private static boolean isZeroZero(Term t)
    {
        if(t.getCoefficient() == 0)
        {
            return true;
        }else{ return false; }
    }

    //Sort polynomial based on exponent
    public void sortPoly()
    {
       terms.sort(Comparator.comparing(Term::getExponent).reversed());
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public void addTerm(Term t)
    {
        this.terms.add(t);
    }

    public Term getTerm(int i)
    {
        return this.terms.get(i);
    }

    public int getNumTerms()
    {
        return this.terms.size();
    }

    public void clear()
    {
        this.terms.removeAll(this.terms);
    }
}
