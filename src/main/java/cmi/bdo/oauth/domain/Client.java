package cmi.bdo.oauth.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 7:11 PM
 */

@Entity
@Table(schema = "bdo_oauth", name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "client_app", nullable = false)
    private Long app;

    @NotNull
    @Column(name = "client_key", nullable = false, unique = true)
    private Integer key;

    @NotNull
    @Column(name = "client_secret", nullable = false, unique = true)
    private String secret;

    @NotNull
    @Column(name = "client_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "client_description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "client_uri", nullable = false)
    private String uri;

    @Column(name = "client_active")
    private Integer active;

    @Column(name = "client_created")
    private String created;

    @Column(name = "client_updated")
    private String updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApp() {
        return app;
    }

    public void setApp(Long app) {
        this.app = app;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}
