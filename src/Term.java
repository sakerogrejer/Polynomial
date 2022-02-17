public class Term implements Comparable, Cloneable
{

    private int coef;
    private int exp;

    public Term(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public Term() {
        this.coef = 0;
        this.exp = 1;
    }

    public Term(String t) {
        this.coef = Integer.parseInt(String.valueOf(t.charAt(0)));
        this.exp = Integer.parseInt(String.valueOf(t.charAt(1)));
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
