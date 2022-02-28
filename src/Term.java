import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term implements Comparable, Cloneable
{

    //Group 1 is Coef, Group 2 is variable, Group 3 is Exp
    private String pattern = "^([+\\-]?\\d*)([a-z]?)\\^?(-?\\d*)$";
    Pattern ptrn = Pattern.compile(pattern);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return coef == term.coef && exp == term.exp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coef, exp);
    }

    //Pain
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();

        System.out.println("Coef: " + coef + " Exp: " + exp);

        if(coef == -1 && exp == 1)
        {
            return "-x";
        }

        if(coef == 0)
        {
            return "";
        }

        if(coef >= 0)
        {
            s.append("+");
        }
        else if(coef == -1)
        {
            s.append("-");
        }

        if(Math.abs(coef) == 1)
        {
            s.append("x");
        }
        else
        {
            s.append(coef);
        }

        if(exp < 0 || exp > 1)
        {
                s.append("x^");
                s.append(exp);
        }
        else if(exp == 1 && !s.toString().equals("+x"))
        {
            s.append("x");
        }

        String str = s.toString();
        str = str.replaceFirst("xx", "x");

        return str;

    }

    private int coef;
    private int exp;

    public Term(int coef, int exp) {
        this.coef = coef;
        this.exp  = exp;
    }

    public Term() {
        this.coef = 1;
        this.exp  = 1;
    }

    //Group 1 is Coef, Group 2 is variable, Group 3 is Exp
    public Term(String t)
    {
        Matcher m = ptrn.matcher(t);

        //Check for +x and -x only
        if(t.equals("+x") || t.equals("-x"))
        {
            if(t.equals("-x"))
            {
                this.coef = -1;
            }
            else
            {
                this.coef = 1;
            }
            this.exp = 1;
        }
        //Else split the term
        else if(m.find())
        {

            //Get capture groups
            String g0 = m.group(0);
            String g1 = m.group(1);
            String g2 = m.group(2);

            //Defaulting empties to 1 and 1 still mathematically correct
            if(g0.equals(""))
            {
                g0 = "1";
            }
            if(g2.equals(""))
            {
                g2 = "0";
            }

            //Checking if there is either no exponent or coef
            if(g2.equals("x"))
            {

                //Check if the solo X is + or minus
                if(g1.equals("+") || g1.equals("-"))
                {
                    this.exp = Integer.parseInt(g0.substring(3));
                    if(g1.equals("+"))
                    {
                        this.coef = 1;
                    }
                    else
                    {
                        this.coef = -1;
                    }

                }

                //Otherwise check either expless or coefless term
                else
                {
                    int expStart = g0.indexOf("^");
                    this.exp = 1;
                    if(expStart >= 0)
                    {
                        this.exp = Integer.parseInt(g0.substring(expStart+1));
                    }
                    else
                    {
                        this.exp = 1;
                    }
                    this.coef = Integer.parseInt(g1);
                }

            }
            else
            {
                //Parse coef and exp
                this.coef = Integer.parseInt(g0);
                this.exp  = Integer.parseInt(g2);
            }

        }

    }

    public Term addTerm(Term t)
    {
        return new Term(t.getCoefficient()+this.coef, t.getExponent());
    }

    public Term(Term original) {
        Term t = new Term(original.coef, original.exp);
        this.exp = t.exp;
        this.coef = t.coef;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getCoefficient() {
        return coef;
    }

    public void setCoefficient(int coef) {
        this.coef = coef;
    }

    public int getExponent() {
        return exp;
    }

    public void setExponent(int exp) {
        this.exp = exp;
    }

    @Override
    public int compareTo(Object o) {
        Term t = (Term)o;
        if(this.exp > t.exp)
        {
            return 1;
        }else if(this.exp == t.exp)
        {
            return 0;
        }else
        {
            return -1;
        }
    }

    public void setAll(int c, int e)
    {
        this.exp = e;
        this.coef = c;
    }
}
