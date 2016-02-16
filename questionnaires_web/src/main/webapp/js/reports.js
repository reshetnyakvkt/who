/**
 * Created by victor_reshetnyak on 02.11.2015.
 */
var sel_form_title=document.getElementById('sel_form_title');
var form_list=document.getElementById('form_list');
var imp_from=document.getElementById('imp_from');
var imp_to=document.getElementById('imp_to');
var imp_all=document.getElementById('imp_all');
var btn_find=document.getElementById('btn_find');
if (btn_find === undefined){
    console.log('btn_find - undefined');
} else {
    console.log('btn_find - defined');
}
btn_find.setAttribute('onclick', 'findFormTitle()');

var ajax = new XMLHttpRequest();
ajax.onreadystatechange = function() {
    if (ajax.readyState == 4) {
        if (ajax.status == 200) {
            if (ajax.response != null) {
                var obj_json = JSON.parse(ajax.response);
                actResponse(obj_json);
            }
        }
    }
}

function actResponse(obj_json){
    console.log('actResponse(' + obj_json [0]+ ')');
    if (obj_json == null || obj_json.length == 0) return;
    switch (obj_json[0]){
        case 0:
            bldReportForm(obj_json[1]);
            break;
        case 1:
            exportToCsv(obj_json[1])
            break;
    }
}

function findFormTitle(page){
    console.log('findFormTitle()-start');
    var url_value = '/reportsformpage_j?act=0&' + getUrlValue(page);
    ajax.open('GET', url_value, true);
    ajax.send();
}

function saveToCSV(id){
    ajax.open('GET', '/reportsformpage_j?act=1&' + getUrlValue(), true);
    ajax.send();
}

function getUrlValue(page){
    var url_value='title_id=' + sel_form_title.value;
    url_value +='&date_from=' + imp_from.value;
    url_value +='&date_to=' + imp_to.value;
    url_value +='&all=' + imp_all.checked;
    if (page === undefined) page=0;
    url_value +='&page=' + page;
    return url_value;
}

function bldReportForm(ar_req){
    var report_forms=ar_req[0];
    form_list.innerHTML = '';
    if (report_forms === undefined || report_forms == null || report_forms.length == 0) {
        console.log('bldReportForm() report_forms is null');
        return;
    }
    var rep_table = document.createElement('table');
    rep_table.id='table_forms';
    var rows;
    var i = 0;
    while (i < report_forms.length){
        if (i % 2 == 0) {
            rows = "even";
        } else {
            rows = "odd";
        }
        var rep_tr = document.createElement('tr');
        rep_tr.setAttribute('class', rows);

        var rep_td = document.createElement('td');
        rep_td.innerHTML = report_forms[i].formId;
        rep_tr.appendChild(rep_td);

        var rep_td = document.createElement('td');
        rep_td.innerHTML = report_forms[i].shortValues;
        rep_tr.appendChild(rep_td);

        var rep_td = document.createElement('td');
        rep_td.innerHTML = formatDate(report_forms[i].createDate);
        rep_tr.appendChild(rep_td);

        rep_table.appendChild(rep_tr);
        report_forms[i]
        i++;
    }
    var btn_csv=document.createElement('button');
    btn_csv.id='btn_csv';
    btn_csv.innerHTML='Import to CSV';
    btn_csv.setAttribute('onclick', 'saveToCSV()');
    form_list.appendChild(rep_table);
    form_list.appendChild(getNavigate(ar_req[1]));
    form_list.appendChild(btn_csv);
}

function getNavigate(rep_title){
    var cnt_btn=3; //visible button
    var div_nav = document.createElement('div');
    var numPage=rep_title.numPage;
    var pages=rep_title.pages;
    console.log('getNavigate: \n' +
            'numPage=' + numPage + ',\n' +
            'pages=' + pages);
    div_nav.id='d_nav';
    //--Prev
    if (pages > cnt_btn){
        div_nav.appendChild(bldBtnNav(-2, numPage, pages));
        div_nav.appendChild(bldBtnNav(-1, numPage, pages));
    }
    //--Pages
    var i_from;
    var i_to;
    var i;
    if (pages <= cnt_btn){
        i_to=pages;
    } else {
        if (numPage < cnt_btn){
            i_to = cnt_btn;
        } else {
            i_to=Math.min(pages, numPage + 1);
        }
    }
    i_from=Math.max(1, i_to - cnt_btn + 1);
    if (i_from > 1) div_nav.appendChild(bldThreeDots());
    for (i = i_from; i <= i_to; i++){
        div_nav.appendChild(bldBtnNav(i, numPage, pages));
    }
    //--Next
    if (i_to < pages) div_nav.appendChild(bldThreeDots());
    if (pages > cnt_btn){
        div_nav.appendChild(bldBtnNav(-3, numPage, pages));
        div_nav.appendChild(bldBtnNav(-4, numPage, pages));
    }
    return div_nav;
}

function bldBtnNav(num, curNum, maxNum){
    var btn = document.createElement('button');
    btn.setAttribute('class', 'btn_nav');
    var goNum;
    switch (num){
        case -1:
            btn.innerHTML = '<';
            goNum = Math.max(1, curNum - 1);
            break;
        case -2:
            btn.innerHTML = '<<';
            goNum = 1;
            break;
        case -3:
            btn.innerHTML = '>';
            goNum=Math.min(maxNum, curNum + 1);
            break;
        case -4:
            btn.innerHTML = '>>';
            goNum = maxNum;
            break;
        default:
            btn.innerHTML = num;
            goNum = num;
    }
    if (num == curNum){
        btn.id='btn_nav_cur';
    } else {
        btn.setAttribute('onclick', 'findFormTitle(' + goNum + ')');
    }
    return btn;
}

function bldThreeDots(){
    var threeDots = document.createElement('a');
    threeDots.innerHTML = '…';
    return threeDots;
}