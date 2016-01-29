package cmi.bdo.oauth.web.rest;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.domain.Client;
import cmi.bdo.oauth.domain.Session;
import cmi.bdo.oauth.domain.User;
import cmi.bdo.oauth.repository.ClientRepository;
import cmi.bdo.oauth.repository.SessionRepository;
import cmi.bdo.oauth.repository.UserRepository;
import cmi.bdo.oauth.security.Encryptor;
import cmi.bdo.oauth.util.DomainUtil;
import cmi.bdo.oauth.validation.AuthValidator;
import cmi.bdo.oauth.validation.UserValidator;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import cmi.bdo.oauth.web.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:07 PM
 */

@RestController
@RequestMapping("api/v1/auth")
public class Authentication {

    private final Logger log = LoggerFactory.getLogger(Authentication.class);

    @Autowired
    private AuthValidator authValidator;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    /**
     * Check if the client key and redirect uri are valid
     *
     * @param clientKey
     * @param redirectUri
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView authenticate(
            @RequestParam(value = "client_key", required = false) String clientKey,
            @RequestParam(value = "redirect_uri", required = false) String redirectUri
    ) {

        AuthResponseDTO authResponseDTO = new AuthResponseDTO() {
            {
                setClientKey(clientKey);
                setRedirectUri(redirectUri);
            }
        };

        String validationMessage = authValidator.validate(authResponseDTO);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", validationMessage);

        return modelAndView;
    }

    /**
     * Submit login form
     *
     * @param clientKey
     * @param redirectUri
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    private ModelAndView login(
            @RequestParam(value = "client_key", required = false) String clientKey,
            @RequestParam(value = "redirect_uri", required = false) String redirectUri,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password
    ) {

        /**
         * Validate if the client key and redirect uri exists in the database
         */

        AuthResponseDTO authResponseDTO = new AuthResponseDTO() {
            {
                setClientKey(clientKey);
                setRedirectUri(redirectUri);
            }
        };

        String validationMessage = authValidator.validate(authResponseDTO);

        /**
         * Validate if the user is existing
         */
        LoginDTO loginDTO = new LoginDTO() {
            {
                setUsername(username);
                setPassword(password);
            }
        };

        if (Constants.SUCCESS.equals(validationMessage))
            validationMessage = userValidator.validate(loginDTO);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("error", validationMessage);
        modelAndView.addObject("message", Constants.SUCCESS);
        modelAndView.addObject("username", loginDTO.getUsername());
        modelAndView.addObject("password", loginDTO.getPassword());

        if (Constants.SUCCESS.equals(validationMessage)) {

            log.info("User with the username: '{}' logged in successfully", username);

            Client client = clientRepository.findOneByKey(Integer.parseInt(clientKey));

            User user = userRepository.findOneByUsernameAndPassword(username, password);

            String responseRedirectUri = client.getUri();

            /**
             * Generate unique code
             */
            StringBuilder sb = new StringBuilder();
            sb.append(client.getKey())
                    .append(client.getUri())
                    .append(user.getUserName())
                    .append(UUID.randomUUID())
                    .append(ZonedDateTime.now())
            ;

            String code;
            try {

                code = Encryptor.sha256(sb.toString());
                responseRedirectUri = DomainUtil.appendUri(responseRedirectUri, "code=".concat(code)).toString();

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException | URISyntaxException e) {
                e.printStackTrace();
                modelAndView.addObject("error", Constants.VALID_USER_LOGIN_ERROR);
                return modelAndView;
            }

            /**
             *  Create session
             */
            Session session = new Session();
            session.setClient(client.getId());
            session.setUser(user.getId());
            session.setCode(code);

            // delete the existing session in the database with the same client id and user id
            sessionRepository.delete(session);

            sessionRepository.save(session);

            log.info("Added user '{}' with the client key '{}' to the database", user.getUserName(), client.getKey());

            return new ModelAndView("redirect:" + responseRedirectUri);
        }

        return modelAndView;
    }

}
