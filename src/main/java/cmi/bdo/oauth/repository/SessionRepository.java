package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/27/2016
 *         Time: 10:05 PM
 */

@Repository
public class SessionRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }


    public void saveSession(Session session) {

        final String sql =
                "INSERT INTO bdo_oauth.session(" +
                        "session_client, " +
                        "\"session_user\", " +
                        "session_code, " +
                        "session_expire, " +
                        "session_created" +
                        ") " +
                        "VALUES (?,?,?,now() + '2 hours'::interval, now());";

        getJdbcTemplate().update(sql, session.getClient(), session.getUser(), session.getCode());

    }

}
