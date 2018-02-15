package es.mdbdev.cvrfparser.model.cvrf.common;

/**
 * Created by Mario on 28/01/2017.
 */
public class Revision
{
    private String Description;

    private String Date;

    private String Number;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getNumber ()
    {
        return Number;
    }

    public void setNumber (String Number)
    {
        this.Number = Number;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", Date = "+Date+", Number = "+Number+"]";
    }
}
