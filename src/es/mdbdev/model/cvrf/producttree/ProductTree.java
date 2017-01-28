package es.mdbdev.model.cvrf.producttree;

/**
 * Created by Mario on 28/01/2017.
 */
public class ProductTree
{
    private Branch Branch;

    private String xmlns;

    public Branch getBranch ()
    {
        return Branch;
    }

    public void setBranch (Branch Branch)
    {
        this.Branch = Branch;
    }

    public String getXmlns ()
    {
        return xmlns;
    }

    public void setXmlns (String xmlns)
    {
        this.xmlns = xmlns;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Branch = "+Branch+", xmlns = "+xmlns+"]";
    }
}
