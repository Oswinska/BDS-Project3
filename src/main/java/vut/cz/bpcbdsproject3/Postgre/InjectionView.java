package vut.cz.bpcbdsproject3.Postgre;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InjectionView
{
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty nickName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    public long getId()
    {
        return id.get();
    }

    public void setId(long id)
    {
        this.id.set(id);
    }

    public LongProperty idProperty()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public void setFirstName(String firstName)
    {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName.get();
    }

    public void setLastName(String lastName)
    {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty()
    {
        return lastName;
    }

    public String getNickName()
    {
        return nickName.get();
    }

    public void setNickName(String nickName)
    {
        this.nickName.set(nickName);
    }

    public StringProperty nickNameProperty()
    {
        return nickName;
    }

    public String getEmail()
    {
        return email.get();
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }

    public StringProperty emailProperty()
    {
        return email;
    }
}
