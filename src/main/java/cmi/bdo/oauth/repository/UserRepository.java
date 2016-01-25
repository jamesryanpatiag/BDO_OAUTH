package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.repository.mapper.UserMapper;
import cmi.bdo.oauth.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

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

    public List<UserDTO> findByUsernameAndPassword(String username, String password) {

        final String sql = "SELECT * " +
                "   FROM bdo_oauth.user " +
                " WHERE user_name = ? " +
                "   AND user_password = ? " +
                "   AND user_active = 1; ";

        List<UserDTO> userDTO = getJdbcTemplate().query(sql,
                new UserMapper(),
                new Object[]{username, password});

        return userDTO;

    }

}
