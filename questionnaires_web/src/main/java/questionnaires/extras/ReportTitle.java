package questionnaires.extras;

import questionnaires.domain.FormTitle;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 03.11.2015
 */
public class ReportTitle {
    private FormTitle formTitle;
    private Date dateFrom;
    private Date dateTo;
    private boolean dateAll;
    private Long pages;
    private Long numPage;
    private Boolean allPages;

    public ReportTitle() {
        this.dateAll = false;
        this.pages = 1L;
        this.numPage = 1L;
    }

    public ReportTitle(FormTitle formTitle, Date dateFrom, Date dateTo, boolean dateAll,
                       Long numPage, Boolean allPages) {
        this();
        this.formTitle = formTitle;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dateAll = dateAll;
        this.numPage = numPage;
        this.allPages = allPages;
    }

    public FormTitle getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(FormTitle formTitle) {
        this.formTitle = formTitle;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getNumPage() {
        return numPage;
    }

    public void setNumPage(Long numPage) {
        this.numPage = numPage;
    }

    public boolean isDateAll() {
        return dateAll;
    }

    public void setDateAll(boolean dateAll) {
        this.dateAll = dateAll;
    }

    public Boolean getAllPages() {
        return allPages;
    }

    public void setAllPages(Boolean allPages) {
        this.allPages = allPages;
    }
}
