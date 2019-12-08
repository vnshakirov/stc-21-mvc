package ru.stc21.internal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.fetchByLoginLength",
        query = "SELECT u FROM User u WHERE CHARACTER_LENGTH(u.login) = :length"
)
public class User implements Serializable {
    @Id
    private String login;
    private String password;
    private String date;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
