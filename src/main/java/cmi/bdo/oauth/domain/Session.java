package cmi.bdo.oauth.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/27/2016
 *         Time: 10:06 PM
 */

@Entity
@Table(schema = "bdo_oauth", name = "session")
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "session_client", nullable = false)
    private Long client;

    @NotNull
    @Column(name = "\"session_user\"", nullable = false)
    private Long user;

    @NotNull
    @Column(name = "session_code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "session_expire", nullable = false)
    private String expire;

    @Column(name = "session_created", nullable = false)
    private String created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
