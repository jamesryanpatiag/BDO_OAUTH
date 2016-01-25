package cmi.bdo.oauth.web.rest;

import cmi.bdo.oauth.domain.Client;
import cmi.bdo.oauth.repository.ClientRepository;
import cmi.bdo.oauth.web.dto.LoginAuthContextDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/23/2016
 *         Time: 12:44 PM
 */

@RestController
@RequestMapping("api/v1/login")
public class Login {

    private final Logger log = LoggerFactory.getLogger(Login.class);

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginAuthContextDTO loginAuthContextDTO) {

        log.info("User with the username: {} logged in successfully", loginAuthContextDTO.getLoginDTO().getUsername());

        log.info("Generating unique code..");

        /**
         * Generate unique code here and add to the session table
         */
        Client client = clientRepository.findOneByKey(Integer.parseInt(loginAuthContextDTO.getAuthResponseDTO().getClientKey()));

        return loginAuthContextDTO.getAuthResponseDTO().getRedirectUri();
    }

}
