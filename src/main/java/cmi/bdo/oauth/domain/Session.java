package cmi.bdo.oauth.domain;

import java.io.Serializable;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/27/2016
 *         Time: 10:06 PM
 */

public class Session implements Serializable {

    private Long id;
    private Long client;
    private Long user;
    private String code;
    private String expire;
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
