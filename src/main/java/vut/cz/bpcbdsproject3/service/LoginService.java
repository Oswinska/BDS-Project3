package vut.cz.bpcbdsproject3.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import vut.cz.bpcbdsproject3.Postgre.LoginView;
import vut.cz.bpcbdsproject3.data.LoginRepository;

public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public boolean login(String email, String password) {
        LoginView loginView = loginRepository.getLoginView(email);
        if (loginView != null) {
            return BCrypt.verifyer().verify(password.toCharArray(), loginView.getHashedPwd()).verified;
        }
        return false;
    }

}
