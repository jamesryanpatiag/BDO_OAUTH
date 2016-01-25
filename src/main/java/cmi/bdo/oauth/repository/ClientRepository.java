package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.repository.mapper.ClientMapper;
import cmi.bdo.oauth.web.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

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

    public ClientDTO findByKey(Integer key) {

        final String sql = "SELECT * " +
                "   FROM bdo_oauth.client" +
                " WHERE client_key = ? " +
                "   AND client_active = 1" +
                " LIMIT 1; ";

        List<ClientDTO> clientDTO = getJdbcTemplate().query(sql,
                new ClientMapper(),
                new Object[]{key});

        if (clientDTO.isEmpty())
            return null;

        return clientDTO.get(0);

    }

}
