package cmi.bdo.oauth.service;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.domain.User;
import cmi.bdo.oauth.repository.UserRepository;
import cmi.bdo.oauth.security.Encryptor;
import cmi.bdo.oauth.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 12:09 PM
 */

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Check if the user exists in the database using the username and password
     *
     * @param loginDTO
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    public String validateUserLogin(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (loginDTO.getUsername() == null ||
                loginDTO.getUsername().trim().length() == 0 ||
                loginDTO.getPassword() == null ||
                loginDTO.getPassword().trim().length() == 0)
            return Constants.INVALID_CREDENTIALS;

        User user = userRepository.findOneByUsernameAndPassword(loginDTO.getUsername(), Encryptor.sha512(loginDTO.getPassword()));

        if (user != null)
            return Constants.SUCCESS;

        return Constants.INVALID_CREDENTIALS;

    }

}
