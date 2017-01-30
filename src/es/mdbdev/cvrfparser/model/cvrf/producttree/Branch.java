package es.mdbdev.cvrfparser.model.cvrf.producttree;

/**
 * Created by Mario on 28/01/2017.
 */
public class Branch
{
    private String Name;

    private FullProductName FullProductName;

    private String Type;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public FullProductName getFullProductName ()
    {
        return FullProductName;
    }

    public void setFullProductName (FullProductName FullProductName)
    {
        this.FullProductName = FullProductName;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", FullProductName = "+FullProductName+", Type = "+Type+"]";
    }
}
