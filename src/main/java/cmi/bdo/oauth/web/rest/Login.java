package cmi.bdo.oauth.web.rest;

import cmi.bdo.oauth.domain.Client;
import cmi.bdo.oauth.domain.Session;
import cmi.bdo.oauth.domain.User;
import cmi.bdo.oauth.repository.ClientRepository;
import cmi.bdo.oauth.repository.SessionRepository;
import cmi.bdo.oauth.repository.UserRepository;
import cmi.bdo.oauth.security.Encryptor;
import cmi.bdo.oauth.util.DomainUtil;
import cmi.bdo.oauth.web.dto.LoginAuthContextDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.UUID;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(method = RequestMethod.POST, produces = "text/plain")
    public String login(@Valid @RequestBody LoginAuthContextDTO loginAuthContextDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException {

        log.info("User with the username: {} logged in successfully", loginAuthContextDTO.getLoginDTO().getUsername());

        log.info("Generating unique code..");

        Client client = clientRepository.findOneByKey(Integer.parseInt(loginAuthContextDTO.getAuthResponseDTO().getClientKey()));

        User user = userRepository.findOneByUsernameAndPassword(loginAuthContextDTO.getLoginDTO().getUsername(), Encryptor.sha512(loginAuthContextDTO.getLoginDTO().getPassword()));

        String redirectUri = client.getUri();

        /**
         * Generate unique code here
         */

        StringBuilder sb = new StringBuilder();
        sb.append(client.getKey())
                .append(client.getUri())
                .append(user.getUserName())
                .append(UUID.randomUUID())
                .append(ZonedDateTime.now())
        ;

        String code = Encryptor.sha256(sb.toString());

        redirectUri = DomainUtil.appendUri(redirectUri, "code=".concat(code)).toString();

        log.info("Done generating the code.");

        log.info("Saving session to the database...");

        Session session = new Session();
        session.setClient(client.getId());
        session.setUser(user.getId());
        session.setCode(code);

        sessionRepository.saveSession(session);

        log.info("Session was saved to the database. Sending redirect uri");

        return redirectUri;

    }

}
