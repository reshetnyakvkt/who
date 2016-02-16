package questionnaires.controller;

import javafx.beans.binding.StringBinding;
import org.springframework.beans.factory.annotation.Value;
import questionnaires.domain.FormValue;
import questionnaires.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import questionnaires.service.AuthenticationService;
import questionnaires.service.FormValueService;

import java.util.StringTokenizer;

/**
 Created by IntelliJ IDEA.
 User: reashetnyak_viktor
 */
@Controller
public class AuthenticationController {
    public static final Logger log = Logger.getLogger(AuthenticationController.class);


    private User user;

    @Autowired(required = true)
    private AuthenticationService authService;

    @Autowired(required = true)
    private FormValueService formValueService;

    public AuthenticationController() {
    }

    public User getUser() {
        return user;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public
    String form(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            Model model,
            HttpServletRequest request)  {
        log.info("/auth:start");
        String submit = request.getParameter("submit");
        String error_message=null;

        log.info("/auth:label-1");

        try {
            if (login == null || login.equals("")){
                error_message = "Error: login is empty";
            } else {
                user = authService.getUserByLogin(login);
                if (user == null) {
                    error_message = "Error: bad login";
                } else {
                    if (authService.authenticate(login, password)) {
                        HttpSession session = request.getSession(true);
                        authService.setSesUserIsDefined(session, true);
                        authService.setSesUser(session, user);
                        log.info("/auth:label-2");

                        model.addAttribute("login", user.getLogin());
                        model.addAttribute("permission", user.getPermission());
                        FormValue lastFormValue = formValueService.getLastFormValue();
                        if (lastFormValue != null){
                            model.addAttribute("last_edit", lastFormValue.getLastEditDate());
                        }

                        return "/dashboard";
                    } else {
                        error_message = "Error: bad password";
                    }
                }
            }
        } catch (questionnaires.exception.AuthenticationException er) {
            log.info("/auth:label-error1");
            er.printStackTrace();
            error_message = er.getMessage();
        }


        log.info("/auth:label-3");
        model.addAttribute("error_auth", true);
        model.addAttribute("error_message", error_message);
        model.addAttribute("login", login);
        return "index";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String logout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        authService.removeSesParameters(session, authService.getSesUser(session));
        log.info("/logout");
        user = null;
        return "/index";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(Model model) {
        log.info("/index questionnaires.controller");
        return "/index";
    }
}
