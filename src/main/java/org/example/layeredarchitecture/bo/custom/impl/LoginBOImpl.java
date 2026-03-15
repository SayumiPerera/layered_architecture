package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.LoginBO;

public class LoginBOImpl implements LoginBO {

    @Override
    public boolean authenticate(String username, String password) {
        return "s".equals(username) && "s".equals(password);
    }
}