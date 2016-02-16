/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 23.10.2015
 */
var div_main_menu = document.getElementById('main_menu');
if (div_main_menu != null){
    div_main_menu.innerHTML = '';
    var ul_menu = document.createElement('ul');
    ul_menu.setAttribute('id', 'nav')

    var li_menu = document.createElement('li');
    var a_item = document.createElement('a');
    a_item.setAttribute('href', '/dataentry');
    a_item.innerHTML = 'Fill form';
    li_menu.appendChild(a_item);
    ul_menu.appendChild(li_menu);

    var li_menu = document.createElement('li');
    var a_item = document.createElement('a');
    a_item.setAttribute('href', '/reports');
    a_item.innerHTML = 'Reports';
    li_menu.appendChild(a_item);
    ul_menu.appendChild(li_menu);

    var li_menu = document.createElement('li');
    var a_item = document.createElement('a');
    a_item.setAttribute('href', '/logout');
    a_item.innerHTML = 'Log out';
    li_menu.appendChild(a_item);
    ul_menu.appendChild(li_menu);

    div_main_menu.appendChild(ul_menu);
} else {
    console.log('menu.js/element not found');
}