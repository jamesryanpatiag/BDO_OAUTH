package cmi.bdo.oauth.service;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.domain.Client;
import cmi.bdo.oauth.repository.ClientRepository;
import cmi.bdo.oauth.util.DomainUtil;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidatorContext;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/23/2016
 *         Time: 2:09 PM
 */

@Service
public class AuthenticationService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean isValidClient(AuthResponseDTO authResponseDTO, ConstraintValidatorContext constraintValidatorContext) {

        Client client = clientRepository.findOneByKey(Integer.parseInt(authResponseDTO.getClientKey()));

        if (client != null) {

            String clientDomain = DomainUtil.getDomain(client.getUri());
            String redirectUriDomain = DomainUtil.getDomain(authResponseDTO.getRedirectUri());

            if (clientDomain == null || redirectUriDomain == null) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(Constants.ERROR_PARSING_DOMAIN)
                        .addConstraintViolation();
                return false;
            }

            if (clientDomain.equals(redirectUriDomain))
                return true;

        }

        constraintValidatorContext
                .buildConstraintViolationWithTemplate(Constants.CLIENTKEY_REDIRECTURI_NOT_FOUND)
                .addConstraintViolation();
        return false;

    }

}
