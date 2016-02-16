package questionnaires.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import questionnaires.domain.User;
import questionnaires.service.AuthenticationService;

/**
 * Created by D2R2 on 23.10.2015.
 */
@Controller
public class FormsController {
    public static final Logger log = Logger.getLogger(AuthenticationController.class);

    private User user;

    @Autowired(required = true)
    private AuthenticationService authService;

    public FormsController() {
    }
}
