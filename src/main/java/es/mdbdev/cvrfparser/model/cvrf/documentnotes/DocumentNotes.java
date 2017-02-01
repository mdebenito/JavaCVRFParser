package es.mdbdev.cvrfparser.model.cvrf.documentnotes;

import es.mdbdev.cvrfparser.model.cvrf.common.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentNotes
{
    public DocumentNotes(){
        this.notes = new ArrayList<Note>();
    }

    public void addNote(Note n){
        this.notes.add(n);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    private List<Note> notes;



    @Override
    public String toString()
    {
        return "ClassPojo [Notes = "+notes+"]";
    }
}
