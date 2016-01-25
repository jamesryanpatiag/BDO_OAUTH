package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 6:55 PM
 */

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE client_keys = ?1 AND client_active = 1")
    Client findOneByKey(Integer key);

}
