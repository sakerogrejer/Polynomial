import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term implements Comparable, Cloneable
{

    private String pattern = "^(-?\\d*)([a-z]?)\\^?(-?\\d*)$";
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

    @Override
    public String toString() {
        if(coef == 0)
        {
            return "";
        }
        else if(exp == 0 && coef > 0)
        {
            return "+" + coef;
        }
        else if(exp == 0 && coef < 0)
        {
            return String.valueOf(coef);
        }else if(coef == 1 || coef == -1)
        {
            if(exp == 1) {
                if(coef == 1)
                {
                    return "+x";
                }else
                {
                    return "-x";
                }

            } else
            {
                if(coef > 0) {
                    return "+x^" + exp;
                } else
                {
                    return "-x^" + exp;
                }
            }
        }
        else if(exp == 1)
        {
            if (coef > 0)
            {
                return "+" + coef + "x";
            }
                else
            {
                {
                    return coef + "x";
                }
            }
        }
        {
            if(coef > 0) {
                return "+" + coef + "x^" + exp;
            }else
            {
                return coef + "x^" + exp;
            }
        }
    }

    private int coef;
    private int exp;

    public Term(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public Term() {
        this.coef = 1;
        this.exp = 1;
    }

    public Term(String t) {
        Matcher m = ptrn.matcher(t);
        if(t.equals("+x") || t.equals("x"))
        {
            this.coef = 1;
            this.exp = 1;
        }
        else if(t.equals("-x"))
        {
            this.coef = -1;
            this.exp = 1;
        }
        else if(t.substring(0,2).equals("-x"))
        {
            this.coef = 1;
            this.exp = Integer.parseInt(t.substring(3));
        }
        else if(m.find())
        {
            if(m.group(0).equals("") && m.group(2).equals(""))
            {
                this.coef = 1;
                this.exp = 1;
            }
            else if(m.group(2).equals(""))
            {
                this.coef = Integer.parseInt(m.group(0));
                this.exp = 1;
            }
            else if(m.group(0).equals(""))
            {
                this.coef = 1;
                this.exp = Integer.parseInt(m.group(2));
            }
            else
            {
                String s = m.group(0);
                s = s.replaceAll("\\D", "");
                this.coef = Integer.parseInt(s);
                this.exp = Integer.parseInt(m.group(2));
            }
        }
        else
        {
            t = t.replaceAll("\\D", "");
            this.coef = Integer.parseInt(t);
            this.exp = 1;
        }

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
