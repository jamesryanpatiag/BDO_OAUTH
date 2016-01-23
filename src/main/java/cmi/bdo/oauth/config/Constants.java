package cmi.bdo.oauth.config;

/**
 * @author Jonathan Leijendekker
 *         Date: 1/13/2016
 *         Time: 12:18 PM
 */
public class Constants {

    /**
     * SPRING PROFILES
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    /**
     * TEMPLATES
     */
    public static final String TEMPLATE_PREFIX = "templates/";
    public static final String TEMPLATE_SUFFIX = ".html";
    public static final String TEMPLATE_MODE = "HTML5";

    /**
     * ERROR MESSAGES
     */
    public static final String BLANK_CLIENTKEY = "Client ID/key can't be null or blank";
    public static final String INVALID_CLIENTKEY_FORMAT = "Invalid client key format";
    public static final String BLANK_REDIRECTURI = "Redirect URI can't be null or blank";

}
