package es.mdbdev.model.cvrf.documentnotes;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentNotes
{
    private Note Note;

    public Note getNote ()
    {
        return Note;
    }

    public void setNote (Note Note)
    {
        this.Note = Note;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Note = "+Note+"]";
    }
}
