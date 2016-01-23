package cmi.bdo.oauth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/19/2016
 *         Time: 10:42 PM
 */

@Controller
public class Default {

    private final Logger log = LoggerFactory.getLogger(Default.class);

    /**
     * RequestMapping for the default page.
     *
     * @return index
     */
    @RequestMapping("*")
    public String getIndexPage() throws SQLException {

        log.info("Rendering the index page");

        return "index";
    }

}
