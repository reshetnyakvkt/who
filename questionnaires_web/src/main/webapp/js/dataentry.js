/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 23.10.2015
 */
var sel_form_title=document.getElementById('sel_form_title');
var btn_new_form=document.getElementById('btn_new_form');
var form_page = document.getElementById('form_page');
var formBodyList;
var form_value={
    imp_values: [],
    add_imp_value: function(in_imp_id, in_id){
        if (this.imp_values === undefined) {
            this.imp_values = new Array(1);
        }
        var cr_imp_value = new Object();
        cr_imp_value.imp_id = in_imp_id;
        cr_imp_value.id = in_id;
        this.imp_values.push(cr_imp_value);
        return;
    },
    get_imp_values: function(){
        var res = '';
        var i = 0;
        res += 'title_id=' +  sel_form_title.value;

        while (i < this.imp_values.length){
            res += '&';
            res += this.imp_values[i].id + '=';
            var imp_val = document.getElementById(this.imp_values[i].imp_id);
            var imp_type = imp_val.getAttribute('type');
            if (imp_val !== undefined){
                if (imp_type == 'checkbox'){
                    res += imp_val.checked;
                } else {
                    res += imp_val.value;
                }
            }
            i++;
        }
        return res;
    },
    clear_imp_value: function(){
        while (this.imp_values.length) {
            this.imp_values.pop();
        }
    }
}

var ajax = new XMLHttpRequest();
ajax.onreadystatechange = function() {
    if (ajax.readyState == 4) {
        if (ajax.status == 200) {
            var form_json = JSON.parse(ajax.response);
            if (form_json.length > 0) {
                var form_action;
                if (form_json.length > 0) form_action = form_json[0];
                var form_obj;
                if (form_json.length > 1) form_obj = form_json[1];
                var subj;
                if (form_json.length > 2) subj = form_json[2];
                console.log('form_action=' + form_action);
                switch (form_action) {
                    case 0: bldSelFormTitle(form_obj);
                        break;
                    case 2:
                        bldForm(form_obj, subj);
                        break;
                    case 3:
                        if (form_json.length < 2) subj=1;
                        setNotifySave(form_obj, subj);
                        break
                    case 4:
                        exportToCsv(form_obj);
                        break
                    case 5:
                        if (form_json.length > 3) formBodyList = form_json[3];
                        bldForm();
                        break
                }
            }
        }
    }
}

loadFormTitle();

function bldForm() {
    form_page.innerHTML = '';
    var table_page=document.createElement('table');
    if (formBodyList != null){
        console.log('bldForm: formBodyList.length=' + formBodyList.length);
        form_page.removeAttribute('hidden');
        form_value.clear_imp_value();
        var i = 0;
        var formBody;
        while (i < formBodyList.length){
            var lbl_val = document.createElement('label');
            var input_val = document.createElement('input');
            formBody = formBodyList[i];
            if (formBody != null){
                if (formBody.paramDict != null){
                    var paramDict = formBody.paramDict;
                    lbl_val.innerHTML = paramDict.name;
                }else {
                    console.log('bldForm: paramDict is null');
                }
                if (formBody.paramType != null){
                    input_val.setAttribute('type', getImpType(formBody.paramType.name));
                }else {
                    console.log('bldForm: paramType is null');
                }
            } else {
                console.log('bldForm: formBody is null');
            }
            var imp_id = 'input_id_' + formBody.id;
            input_val.setAttribute('id', imp_id);
            form_value.add_imp_value(imp_id, formBody.id);
            var tr_page=document.createElement('tr');
            tr_page.appendChild(getTdByPerc(lbl_val, 20));
            tr_page.appendChild(getTdByPerc(input_val, 60));
            table_page.appendChild(tr_page);
            i++;
        }
        form_page.appendChild(table_page);

        if (formBodyList.length > 0){
            var btn_save_continue = document.createElement('button');
            btn_save_continue.setAttribute('onclick', 'saveAndNew()');
            btn_save_continue.innerHTML = 'Save and fill in a new form';
            form_page.appendChild(btn_save_continue);

            var btn_cancel = document.createElement('button');
            btn_cancel.setAttribute('onclick', 'cancel()');
            btn_cancel.innerHTML = 'Cancel';
            form_page.appendChild(btn_cancel);

            sel_form_title.setAttribute('disabled', '1');
            btn_new_form.setAttribute('hidden', '1');
        } else {
            var p_empty = document.createElement('p');
            p_empty.innerHTML='Not found elements from form!';
            p_empty.setAttribute('color', 'red');
            form_page.appendChild(p_empty);
        }
    } else {
        form_page.setAttribute('hidden', 'true');
        sel_form_title.removeAttribute('disabled');
        btn_new_form.removeAttribute('disabled');
        console.log('bldForm: formBodyList is null');
    }
}

function getTdByPerc(item_td, size_td){
    var td_page=document.createElement('td');
    td_page.setAttribute('width', size_td + '%');
    td_page.appendChild(item_td);
    return td_page
}
function bldSelFormTitle(formTitleList){
    sel_form_title.innerHTML='';
    form_page.setAttribute('hidden', 'true');
    form_page.innerHTML='';

    if (formTitleList == null || formTitleList.length == 0) {
        sel_form_title.setAttribute('disabled', 'true');
        btn_new_form.setAttribute('hidden', 'true');
        btn_new_form.removeAttribute('onclick');
        return;
    }
    sel_form_title.removeAttribute('disabled');
    console.log('bldSelFormTitle:formTitleList.length=' + formTitleList.length);
    var i = 0;
    while (i <  formTitleList.length){
        var option_item = document.createElement('option');
        option_item.setAttribute('value', formTitleList[i].id);
        option_item.innerHTML=formTitleList[i].name;
        sel_form_title.appendChild(option_item);
        i++;
    }
    if (formTitleList.length > 0){
        btn_new_form.removeAttribute('hidden');
        btn_new_form.setAttribute('onclick', 'addNewForm()');
    }
}

function loadFormTitle() {
    console.log('loadFormTitle()-start');
    ajax.open('GET', '/dataentry_j', true);
    ajax.send();
}

function addNewForm(){
    var id = sel_form_title.value;
    sel_form_title.setAttribute('disabled', '1');
    btn_new_form.setAttribute('disabled', '1');
    ajax.open('GET', '/dataentryaddnewform_j?id=' + id, true);
    ajax.send();
}

function setNotifySave(id, n_state){
    var img_notify = document.getElementById('img_notify_' + id);
    if (img_notify !== undefined){
        img_notify.removeAttribute('alt');
        img_notify.removeAttribute('src');
        switch (n_state){
            case 0:
                img_notify.setAttribute('alt', 'Ok');
                img_notify.setAttribute('src', '../../img/notify_ok16x16.png');
                img_notify.setAttribute('name', img_notify.value);
                break;
            case 1:
                img_notify.setAttribute('alt', 'Save');
                img_notify.setAttribute('src', '../../img/notify_save16x16.png');
                break;
            case 2:
                img_notify.setAttribute('alt', 'Error');
                img_notify.setAttribute('src', '../../img/notify_err16x16.png');
                break;
            case 3:
                img_notify.setAttribute('alt', 'Edit');
                img_notify.setAttribute('src', '../../img/notify_edit16x16.png');
                break;
        }
    }
}

function getImpType(name){
    console.log('getImpType(): name=' + name);
    if (name === undefined || name == null) return '';
    var name = name.toUpperCase();
    if (name == 'STRING') return 'text';
    if (name == 'NUMBER') return 'number';
    if (name == 'BOOLEAN (YES\\NO)') return 'checkbox';
    if (name == 'DATE') return 'date';
    if (name == 'ENUM') return 'search';
}

function saveToCSV(id){
    ajax.open('GET', '/loadformtocsv_j?id=' + id, true);
    ajax.send();
}

function cancel(){
    sel_form_title.removeAttribute('disabled');
    btn_new_form.removeAttribute('disabled');
    btn_new_form.removeAttribute('hidden');
    form_page.innerHTML='';
    form_page.setAttribute('hidden', '1');
}

function saveAndNew(){
    form_page.setAttribute('disabled', '1');
    var params=form_value.get_imp_values();
    ajax.open('POST', '/datasavevalue_j', true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send(params);
}
