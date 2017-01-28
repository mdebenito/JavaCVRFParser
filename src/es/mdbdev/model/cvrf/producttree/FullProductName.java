package es.mdbdev.model.cvrf.producttree;

/**
 * Created by Mario on 28/01/2017.
 */
public class FullProductName
{
    private String content;

    private String ProductID;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getProductID ()
    {
        return ProductID;
    }

    public void setProductID (String ProductID)
    {
        this.ProductID = ProductID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", ProductID = "+ProductID+"]";
    }
}
