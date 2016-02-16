package questionnaires.service;

import questionnaires.extras.ReportForm;
import questionnaires.extras.ReportTitle;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by D2R2 on 03.11.2015.
 */
public interface ReportsService {
    List<ReportForm> findReportForm(Long userId, ReportTitle reportTitle);
    ReportTitle getReportTitle(HttpSession session);
}
