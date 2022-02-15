public class Term implements Comparable
{

    private int coef;
    private int exp;

    public Term(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
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
}
