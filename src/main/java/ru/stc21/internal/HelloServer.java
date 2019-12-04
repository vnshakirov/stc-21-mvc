package ru.stc21.internal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("application")
public class HelloServer {

    private String helloString;

    public void setHelloString(String helloString) {
        this.helloString = helloString;
    }

    public String getHelloString() {
        return helloString;
    }
}
