import java.util.*;

public class Polynomial implements Cloneable
{

    protected LinkedList<Term> terms;
    Scanner scanner = new Scanner(System.in);

    //Constructors
    public Polynomial(LinkedList<Term> terms) {
        this.terms = terms;
        sortPoly();
    }

    public Polynomial()
    {
        this.terms = new LinkedList<Term>();
        sortPoly();
    }

    public Polynomial(Polynomial original) {
        this.terms = new LinkedList<Term>();
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
    public Polynomial add(Polynomial x) {

        x.sortPoly();
        this.sortPoly();

        Polynomial p = new Polynomial(new LinkedList<Term>());
        Polynomial y = this;

        Polynomial temp1 = new Polynomial(x);
        Polynomial temp2 = new Polynomial(y);

        //Adding Two Poly
        for(Term t1 : x.terms)
        {
            for(Term t2 : y.terms)
            {
                if(t1.getExponent() == t2.getExponent())
                {
                    p.terms.add(new Term(t1.getCoefficient() + t2.getCoefficient(), t1.getExponent()));
                    temp1.terms.remove(t1);
                    temp2.terms.remove(t2);
                }
            }
        }

        temp1.terms.addAll(temp2.terms);

        p.terms.addAll(temp1.terms);
        p.sortPoly();

        p.terms.removeIf(tt -> tt.getCoefficient()==0);

        this.terms = p.terms;
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
        return t.getCoefficient() == 0;
    }

    //Sort polynomial based on exponent
    public void sortPoly()
    {
       terms.sort(Comparator.comparing(Term::getExponent).reversed());
    }

    public LinkedList<Term> getTerms() {
        return terms;
    }

    public void setTerms(LinkedList<Term> terms) {
        this.terms = terms;
    }

    public void addTerm(Term t)
    {
        this.sortPoly();

        int i = 0;
        for(Term tt : this.terms)
        {
            if(tt.getExponent() == t.getExponent())
            {
                this.terms.set(i, tt.addTerm(t));
                this.sortPoly();
                return;
            }
            i++;
        }

        this.terms.add(t);
        this.sortPoly();

    }

    public Term getTerm(int i)
    {
        this.sortPoly();
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
