package es.mdbdev.model.cvrf.documenttracking;

/**
 * Created by Mario on 28/01/2017.
 */
public class Generator
{
    private String Engine;

    public String getEngine ()
    {
        return Engine;
    }

    public void setEngine (String Engine)
    {
        this.Engine = Engine;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Engine = "+Engine+"]";
    }
}
