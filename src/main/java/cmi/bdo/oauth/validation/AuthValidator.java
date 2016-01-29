package cmi.bdo.oauth.validation;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.domain.Client;
import cmi.bdo.oauth.repository.ClientRepository;
import cmi.bdo.oauth.util.DomainUtil;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:26 PM
 */

@Component
public class AuthValidator {

    @Autowired
    private ClientRepository clientRepository;

    public String validate(AuthResponseDTO authResponseDTO) {

        if (authResponseDTO.getClientKey() == null ||
                authResponseDTO.getClientKey().trim().length() == 0) {

            return Constants.BLANK_CLIENTKEY;
        } else if (authResponseDTO.getRedirectUri() == null ||
                authResponseDTO.getRedirectUri().trim().length() == 0) {

            return Constants.BLANK_REDIRECTURI;
        }

        try {
            Integer.parseInt(authResponseDTO.getClientKey());
        } catch (Exception e) {
            return Constants.INVALID_CLIENTKEY_FORMAT;
        }

        Client client = clientRepository.findOneByKey(Integer.parseInt(authResponseDTO.getClientKey()));

        if (client != null) {

            String clientDomain = DomainUtil.getDomain(client.getUri());
            String redirectUriDomain = DomainUtil.getDomain(authResponseDTO.getRedirectUri());

            if (clientDomain == null || redirectUriDomain == null)
                return Constants.ERROR_PARSING_DOMAIN;


            if (clientDomain.equals(redirectUriDomain))
                return Constants.SUCCESS;

        }

        return Constants.CLIENTKEY_REDIRECTURI_NOT_FOUND;

    }

}
