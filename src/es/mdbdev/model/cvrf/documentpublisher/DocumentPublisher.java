package es.mdbdev.model.cvrf.documentpublisher;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentPublisher
{
    private String IssuingAuthority;

    private String Type;

    private String ContactDetails;

    public String getIssuingAuthority ()
    {
        return IssuingAuthority;
    }

    public void setIssuingAuthority (String IssuingAuthority)
    {
        this.IssuingAuthority = IssuingAuthority;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getContactDetails ()
    {
        return ContactDetails;
    }

    public void setContactDetails (String ContactDetails)
    {
        this.ContactDetails = ContactDetails;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IssuingAuthority = "+IssuingAuthority+", Type = "+Type+", ContactDetails = "+ContactDetails+"]";
    }
}
