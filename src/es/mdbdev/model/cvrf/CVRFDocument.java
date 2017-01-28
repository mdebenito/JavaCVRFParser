package es.mdbdev.model.cvrf;

/**
 * Created by Mario on 28/01/2017.
 */
public class CVRFDocument
{
    private Cvrfdoc cvrfdoc;

    public Cvrfdoc getCvrfdoc ()
    {
        return cvrfdoc;
    }

    public void setCvrfdoc (Cvrfdoc cvrfdoc)
    {
        this.cvrfdoc = cvrfdoc;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cvrfdoc = "+cvrfdoc+"]";
    }
}