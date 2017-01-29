package es.mdbdev.model.cvrf.documenttracking;

/**
 * Created by Mario on 28/01/2017.
 */
public class Identification
{
    private String ID;

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    private String Alias;

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ID = "+ID+", Alias = "+Alias+"]";
    }
}
