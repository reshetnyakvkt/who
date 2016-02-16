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
import questionnaires.extras.DateUtilities;
import questionnaires.extras.FormExporter;
import questionnaires.extras.ReportForm;
import questionnaires.extras.ReportTitle;
import questionnaires.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 23.10.2015
 */
@Controller
public class ReportsController {
    public static final Logger log = Logger.getLogger(ReportsController.class);
    private List<FormTitle> formTitleList;
    private User user;

    @Autowired(required = true)
    private AuthenticationService authService;

    @Autowired(required = true)
    private ReportsService reportsService;

    @Autowired(required = true)
    private FormService formService;

    @Autowired(required = true)
    private FormValueService formValueService;

    @Autowired(required = true)
    FormTitleService formTitleService;

    public ReportsController() {
    }

    @RequestMapping(value = "/reports", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String reports(Model model, HttpServletRequest request){
        log.info("/reports-start");
        HttpSession session = request.getSession(true);
        User user = authService.getSesUser(session);
        if (! authService.getSesUserIsDefined(session) || user == null){
            return "/index";
        }
        String sFormSelTitle = getFormSelTitle(0);
        model.addAttribute("form_sel_title", sFormSelTitle);

        log.info("/reports-end");
        return "/reports";
    }

    //reportsformpage_j
    @RequestMapping(value = "/reportsformpage_j", method = {RequestMethod.GET})
    public @ResponseBody List<Object> reportspage(@RequestParam Integer act,
            @RequestParam Long title_id, @RequestParam String date_from,
            @RequestParam String date_to, @RequestParam Boolean all, @RequestParam Long page,
            Model model, HttpServletRequest request){
        log.info("/reportspage-start");
        Date dateFrom = new DateUtilities(date_from).getDate();
        Date dateTo = new DateUtilities(date_to).getDate();
        HttpSession session = request.getSession(true);
        User user = authService.getSesUser(session);
        List<Object> res = new ArrayList<>();
        if (! authService.getSesUserIsDefined(session) || user == null){
            res.add(-1); // action - logout
            return res;
        }
        ReportTitle reportTitle = new ReportTitle(formTitleService.read(title_id), dateFrom, dateTo,
                all, page=Math.max(1, page), act == 1);
        switch (act){
            case 0:
                res.add(0); // action
                List<ReportForm> reportFormList =
                        reportsService.findReportForm(user.getId(), reportTitle);
                res.add(Arrays.asList(reportFormList, reportTitle));
                break;
            case 1:
                res.add(1); // action
                List<FormValue> formValueList = formValueService.findByReportTitle(user.getId(), reportTitle);
                FormExporter fileExporter = new FormExporter();
                res.add(fileExporter.getCSV(formValueList));
                break;
        }
        return res;
    }

    private String getFormSelTitle(long userId){
        formTitleList = formTitleService.findByUserId(userId);
        StringBuilder sb = new StringBuilder("");
        sb.append("<div id=\"form_sel_title\">\n" +
                "<table width=\"95%\">\n<tr>\n" +
                "<td width=\"130px\"><a>Select dataset</a></td>\n" +
                "<td><select id=\"sel_form_title\">");
        if (formTitleList == null) {
            log.info("/formTitleList is null");
        } else {
            for (int i = 0; i < formTitleList.size(); i++){
                sb.append("<option value=\"" + formTitleList.get(i).getId() + "\">" +
                        formTitleList.get(i).getName() + "</option>");
            }
        }
        sb.append("</select></td>\n" +
                "</tr>\n");
        if (formTitleList != null || formTitleList.size() > 0) {
            sb.append("<tr>\n<td>Period</td>\n" +
                            "<td>from :<input id=\"imp_from\" type=\"date\"></input>" +
                            "to :<input id=\"imp_to\" type=\"date\"></input>" +
                            " All <input id=\"imp_all\" type=\"checkbox\"></input></td>\n</tr>\n" +
                            "<tr><td></td><td><button id=\"btn_find\">Find</button></td></tr>\n"
            );
        }
        sb.append("</table>\n</div>\n");
        return sb.toString();
    }
}
