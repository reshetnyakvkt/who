package questionnaires.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import questionnaires.domain.FormValue;
import questionnaires.domain.User;
import questionnaires.service.AuthenticationService;
import questionnaires.service.FormValueService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 Created by IntelliJ IDEA.
 User: reashetnyak_viktor
 */
@Controller
public class DashboardController {
    public static final Logger log = Logger.getLogger(DashboardController.class);

    @Autowired(required = true)
    private AuthenticationService authService;

    @Autowired(required = true)
    private AuthenticationController authController;

    @Autowired(required = true)
    private FormValueService formValueService;

    public DashboardController() {
    }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String dashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = authService.getSesUser(session);
        if (! authService.getSesUserIsDefined(session) || user == null){
            return "/index";
        }

        FormValue lastFormValue = formValueService.getLastFormValue();
        if (lastFormValue != null){
            log.info("/dashboard: lastFormValue.getLastEditDate()=" + lastFormValue.getLastEditDate());
            model.addAttribute("last_edit", lastFormValue.getLastEditDate());
        } else {
            log.info("/dashboard: lastFormValue not found");
        }

        return "/dashboard";
    }
}