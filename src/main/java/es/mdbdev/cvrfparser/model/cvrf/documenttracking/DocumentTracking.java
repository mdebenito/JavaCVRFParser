package es.mdbdev.cvrfparser.model.cvrf.documenttracking;

import es.mdbdev.cvrfparser.model.cvrf.common.RevisionHistory;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentTracking
{
    private String Status;

    private String CurrentReleaseDate;

    private String InitialReleaseDate;

    private RevisionHistory RevisionHistory;

    private Generator Generator;

    private Identification Identification;

    private String Version;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getCurrentReleaseDate ()
    {
        return CurrentReleaseDate;
    }

    public void setCurrentReleaseDate (String CurrentReleaseDate)
    {
        this.CurrentReleaseDate = CurrentReleaseDate;
    }

    public String getInitialReleaseDate ()
    {
        return InitialReleaseDate;
    }

    public void setInitialReleaseDate (String InitialReleaseDate)
    {
        this.InitialReleaseDate = InitialReleaseDate;
    }

    public RevisionHistory getRevisionHistory ()
    {
        return RevisionHistory;
    }

    public void setRevisionHistory (RevisionHistory RevisionHistory)
    {
        this.RevisionHistory = RevisionHistory;
    }

    public Generator getGenerator ()
    {
        return Generator;
    }

    public void setGenerator (Generator Generator)
    {
        this.Generator = Generator;
    }

    public Identification getIdentification ()
    {
        return Identification;
    }

    public void setIdentification (Identification Identification)
    {
        this.Identification = Identification;
    }

    public String getVersion ()
    {
        return Version;
    }

    public void setVersion (String Version)
    {
        this.Version = Version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", CurrentReleaseDate = "+CurrentReleaseDate+", InitialReleaseDate = "+InitialReleaseDate+", RevisionHistory = "+RevisionHistory+", Generator = "+Generator+", Identification = "+Identification+", Version = "+Version+"]";
    }
}
