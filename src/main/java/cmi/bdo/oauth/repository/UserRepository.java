package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.domain.User;
import cmi.bdo.oauth.domain.mapper.UserMapper;
import cmi.bdo.oauth.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 11:39 PM
 */

@Repository
public class UserRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public User findOneByUsernameAndPassword(String username, String password) {

        final String sql =
                "SELECT * " +
                        "       FROM bdo_oauth.user " +
                        "   WHERE user_name = ? " +
                        "     AND user_active = 1;";

        User user;

        try {
            user = getJdbcTemplate().queryForObject(sql,
                    new Object[]{username},
                    new UserMapper());

            if (!Encryptor.bcryptMatches(password, user.getPassword()))
                return null;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return user;

    }

}
