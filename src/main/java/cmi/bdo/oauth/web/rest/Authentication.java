package cmi.bdo.oauth.web.rest;

import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.Valid;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:07 PM
 */

@RestController
@RequestMapping("/api/v1/authenticate")
public class Authentication extends JdbcDaoSupport {

    private final Logger log = LoggerFactory.getLogger(Authentication.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthResponseDTO auth) {

        //System.out.println(auth.getClientKey());
        //System.out.println(auth.getRedirectUri());

        return new ResponseEntity(auth, HttpStatus.OK);
    }

}
