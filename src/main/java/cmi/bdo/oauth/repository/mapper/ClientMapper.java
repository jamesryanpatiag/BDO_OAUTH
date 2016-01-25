package cmi.bdo.oauth.repository.mapper;

import cmi.bdo.oauth.web.dto.ClientDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 9:06 PM
 */
public class ClientMapper implements RowMapper {
    @Override
    public ClientDTO mapRow(ResultSet rs, int i) throws SQLException {
        return new ClientDTO() {
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
    }
}
