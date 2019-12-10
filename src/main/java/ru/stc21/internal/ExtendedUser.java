package ru.stc21.internal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

public class ExtendedUser extends User {

    private final String snils;
    private final LocalDate birthDate;

    public ExtendedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String snils, LocalDate birthDate) {
        super(username, password, authorities);
        this.snils = snils;
        this.birthDate = birthDate;
    }

    public String getSnils() {
        return snils;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
