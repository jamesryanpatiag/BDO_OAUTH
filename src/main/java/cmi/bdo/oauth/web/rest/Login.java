package cmi.bdo.oauth.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/23/2016
 *         Time: 12:44 PM
 */

@RestController
@RequestMapping("api/v1/login")
public class Login extends JdbcDaoSupport {

    private final Logger log = LoggerFactory.getLogger(Login.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity login() {

        return null;
    }

}
