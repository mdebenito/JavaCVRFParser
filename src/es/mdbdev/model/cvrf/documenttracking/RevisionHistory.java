package es.mdbdev.model.cvrf.documenttracking;

/**
 * Created by Mario on 28/01/2017.
 */
public class RevisionHistory
{
    private es.mdbdev.model.cvrf.documenttracking.Revision[] Revision;

    public Revision[] getRevision ()
    {
        return Revision;
    }

    public void setRevision (Revision[] Revision)
    {
        this.Revision = Revision;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Revision = "+Revision+"]";
    }
}
