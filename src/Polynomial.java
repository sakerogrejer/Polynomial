import java.util.*;

public class Polynomial implements Cloneable
{

    private ArrayList<Term> terms;
    Scanner scanner = new Scanner(System.in);

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
    public Polynomial add(Polynomial y)
    {

        //Init vars
        Polynomial z = new Polynomial(new ArrayList<>());
        Polynomial leftx = new Polynomial(this.terms);
        Polynomial x = leftx;
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
                if (x.terms.get(k).getExponent() == y.terms.get(n).getExponent())
                {
                    z.terms.add(new Term(x.terms.get(k).getCoefficient() + y.terms.get(k).getCoefficient(), x.terms.get(k).getExponent()));
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

    public Polynomial(ArrayList<Term> terms) {
        this.terms = terms;
        sortPoly();
    }

    public Polynomial()
    {
        this.terms = new ArrayList<>();
        sortPoly();
    }

    public Polynomial(Polynomial p)
    {
        this(p.terms);
        sortPoly();
    }

    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Polynomial(this.terms);
        }
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
