package cmi.bdo.oauth.repository;

import cmi.bdo.oauth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 11:39 PM
 */

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE user_name = ?1 AND user_password = ?2 AND user_active = 1")
    User findOneByUsernameAndPassword(String username, String password);

}
