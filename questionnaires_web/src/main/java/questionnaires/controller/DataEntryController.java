package questionnaires.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import questionnaires.domain.*;
import questionnaires.extras.FormExporter;
import questionnaires.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Created by IntelliJ IDEA.
 User: reashetnyak_viktor
 */
@Controller
public class DataEntryController {
    public static final Logger log = Logger.getLogger(DataEntryController.class);
    private User user;
    private List<FormTitle> formTitleList;
    private List<FormBody> formBodies;

    @Autowired(required = true)
    private AuthenticationService authService;

    @Autowired(required = true)
    private AuthenticationController authController;

    @Autowired(required = true)
    private FormService formService;

    @Autowired(required = true)
    private FormBodyService formBodyService;

    @Autowired(required = true)
    private FormValueService formValueService;

    @Autowired(required = true)
    private FormTitleService formTitleService;

    @Autowired(required = true)
    private ParamTypeService paramTypeService;

    @Autowired(required = true)
    private EnumDictionaryService enumDictionaryService;

    public DataEntryController() {
    }

    @RequestMapping(value = "/dataentry", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String dashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = authService.getSesUser(session);
        if (! authService.getSesUserIsDefined(session) || user == null){
            return "/index";
        }
        return "/dataentry";
    }

    @RequestMapping(value = "/dataentry_j", method = {RequestMethod.GET})
    public @ResponseBody List<Object> dashboard_j(Model model) {
        user = authController.getUser();
        if (user == null) return null;
        model.addAttribute("login", user.getLogin());
        List<Object> res = new ArrayList<>();
        res.add(0); // action

        formTitleList = formTitleService.findByUserId(user.getId());
        if (formTitleList == null) {
            log.info("/dataentry_j-formTitleList is null");
        } else {
            res.add(formTitleList);
            log.info("/dataentry_j-formTitleList.size()=" + formTitleList.size());
        }
        return res;
    }

    @RequestMapping(value = "/dataentryform_j", method = {RequestMethod.GET})
    public @ResponseBody List<Object> dataentryform_j(@RequestParam Long id, Model model) {
        log.info("/dataentryform_j-start");
        user = authController.getUser();
        if (user == null) return null;
        List<Object> res = new ArrayList<>();
        res.add(1); // action

        List<Form> formList = null;
        try{
            formList = formService.findFormByTitleId(user.getId(), id);
        } catch (Exception ex){
            log.info("Error execute formService.findFormTitleAll(): " + ex.getMessage());
        }
        if (formList == null) {
            log.info("/dataentryform_j-formList is null");
        } else {
            res.add(formList);
            log.info("/dataentryform_j-formList.size()=" + formList.size());
            System.out.println(formList.toString());
        }
        log.info("/dataentryform_j-end");
        return res;
    }

    @RequestMapping(value = "/dataentryvolume_j", method = {RequestMethod.GET})
    public @ResponseBody List<Object> dataentryvolume_j(@RequestParam Long id, Model model){
        log.info("/dataentryvolume_j-start");
        user = authController.getUser();
        if (user == null) return null;
        List<Object> res = new ArrayList<>();
        res.add(2); // action

        Form form = formService.getFormById(id); // user.getId();

        if (form == null) {
            log.info("/dataentryvolume_j-form is null");
        } else {
            res.add(formValueService.findByFormId(form.getId()));
            List<FormBody> formBodyList = formBodyService.findAllByFormTitleId(form.getFormTitleId());
            log.info(formBodyList.toString());
            res.add(formBodyList);
        }
        log.info("/dataentryvolume_j-end");
        return res;
    }

    //dataentryaddnewform_j
    @RequestMapping(value = "/dataentryaddnewform_j", method = {RequestMethod.GET})
    public @ResponseBody List<Object> dataentryaddnewform_j(@RequestParam Long id, Model model) {
        log.info("/dataentryaddnewform_j-start");
        user = authController.getUser();
        if (user == null) return null;

        Form form = new Form(user.getId(), id);
        Long formId = formService.createForm(form);

        List<Object> res = new ArrayList<>();
        res.add(5);  // action
        res.add(form);
        if (form == null) {
            log.info("/dataentryaddnewform_j-form is null");
        } else {
            List<FormBody> formBodyList = formBodyService.findAllByFormTitleId(form.getFormTitleId());
            List<FormValue> formValueList = formValueService.fillNotExistsByFormBody(user.getId(),
                    formId, form.getFormTitleId(), formBodyList);
            res.add(formValueList);
            res.add(formBodyList);
        }
        log.info("/dataentryaddnewform_j-end");
        return res;
    }

    //datasavevalue_j
    @RequestMapping(value = "/datasavevalue_j", method = {RequestMethod.POST})
    public @ResponseBody List<Object> datasavevalue_j(Model model, HttpServletRequest request) {
        log.info("/datasavevalue_j-start");
        HttpSession session = request.getSession(true);
        User user = authService.getSesUser(session);
        if (! authService.getSesUserIsDefined(session) || user == null){
             return null;
        }
        model.addAttribute("login", user.getLogin());
        try {
            request.setCharacterEncoding("UTF-8");
            formService.createFormValues(user.getId(), readBuffer(request.getReader()));
        } catch (IOException ex) {
            log.error("Error: " + ex.getMessage());
        }

        List<Object> res = new ArrayList<>();
        res.add(5);  // action

        return res;
    }

    public String readBuffer(BufferedReader reader) {
        StringBuilder buffer = new StringBuilder();
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
        } catch (IOException ex) {
            log.error("Error: " + ex.getMessage());
        }
        return buffer.toString();
    }
}
