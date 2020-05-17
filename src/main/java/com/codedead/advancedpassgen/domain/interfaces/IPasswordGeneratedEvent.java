package com.codedead.advancedpassgen.domain.interfaces;

import com.codedead.advancedpassgen.domain.objects.password.Password;

import java.util.List;

public interface IPasswordGeneratedEvent {

    void passwordsGenerated(List<Password> passwordList);

}
