package cmi.bdo.oauth.domain;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/28/2016
 *         Time: 8:31 PM
 */
public class ResponseData {

    private String value;

    public ResponseData(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
