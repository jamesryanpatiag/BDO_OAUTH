package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.domain.Client;
import cmi.bdo.oauth.domain.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 6:55 PM
 */

@Repository
public class ClientRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public Client findOneByKey(Integer key) {

        final String sql =
                "SELECT * " +
                        "       FROM bdo_oauth.client " +
                        "   WHERE client_key = ? " +
                        "     AND client_active = 1;";

        Client client;

        try {
            client = getJdbcTemplate().queryForObject(sql,
                    new Object[]{key},
                    new ClientMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return client;
    }

}
