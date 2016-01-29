package cmi.bdo.oauth.domain.mapper;

import cmi.bdo.oauth.domain.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 1/29/2016
 *         Time: 1:21 PM
 */
public class ClientMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int i) throws SQLException {

        Client client = new Client() {
            {
                setId(rs.getLong("client_id"));
                setApp(rs.getLong("client_app"));
                setKey(rs.getInt("client_key"));
                setSecret(rs.getString("client_secret"));
                setName(rs.getString("client_name"));
                setDescription(rs.getString("client_description"));
                setUri(rs.getString("client_uri"));
                setActive(rs.getInt("client_active"));
                setCreated(rs.getString("client_created"));
                setUpdated(rs.getString("client_updated"));
            }
        };

        return client;
    }
}
