package cmi.bdo.oauth.web.rest;

import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:07 PM
 */

@RestController
@RequestMapping("/api/v1/authenticate")
public class Authentication {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthResponseDTO auth) {

        return new ResponseEntity(auth, HttpStatus.OK);
    }

}
