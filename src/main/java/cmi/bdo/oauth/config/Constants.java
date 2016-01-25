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
    public static final String CLIENTKEY_REDIRECTURI_NOT_FOUND = "Client key and Redirect URI was not found";
    public static final String ERROR_PARSING_DOMAIN = "Error in parsing the uri";
    public static final String INVALID_CREDENTIALS = "Invalid login credentials";
    public static final String VALID_USER_LOGIN_ERROR = "Cannot login at the moment. Please try again later";

    public static final String SUCCESS = "Success";

}
