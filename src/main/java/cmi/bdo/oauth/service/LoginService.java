package cmi.bdo.oauth.service;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.repository.UserRepository;
import cmi.bdo.oauth.security.Encryptor;
import cmi.bdo.oauth.web.dto.LoginDTO;
import cmi.bdo.oauth.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

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
    public String validateUserLogin(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {

        if (loginDTO.getUsername() == null ||
                loginDTO.getUsername().trim().length() == 0 ||
                loginDTO.getPassword() == null ||
                loginDTO.getPassword().trim().length() == 0)
            return Constants.INVALID_CREDENTIALS;

        List<UserDTO> userDTO = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), Encryptor.sha512(loginDTO.getPassword()));

        if (!userDTO.isEmpty())
            return Constants.SUCCESS;

        return Constants.INVALID_CREDENTIALS;

    }

}
