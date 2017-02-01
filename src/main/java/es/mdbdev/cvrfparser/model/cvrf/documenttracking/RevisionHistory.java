package es.mdbdev.cvrfparser.model.cvrf.documenttracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 28/01/2017.
 */
public class RevisionHistory
{
    public RevisionHistory(){
        this.revisions = new ArrayList<Revision>();
    }
    private List<Revision> revisions;

    public List<Revision> getRevision ()
    {
        return revisions;
    }

    public void addRevision(Revision r){
        this.revisions.add(r);
    }

    public void setRevision (List<Revision> revisions)
    {
        this.revisions = revisions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Revisions = "+revisions+"]";
    }
}
