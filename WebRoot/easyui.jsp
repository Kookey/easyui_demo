<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <h2>Basic CRUD Application</h2>
    <div class="demo-info" style="margin-bottom:10px">
        <div class="demo-tip icon-tip">&nbsp;</div>
        <div>Click the buttons on datagrid toolbar to do crud actions.</div>
    </div>
    
    <table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"
            url="listUser"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="firstName" width="50">名字</th>
                <th field="lastName" width="50">姓</th>
                <th field="phone" width="50">手机</th>
                <th field="email" width="50">邮编</th>
                <td field="userId" width="50" style="display: none;"></td>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">User Information</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>名字:</label>
                <input name="firstName" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>姓:</label>
                <input name="lastName" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>手机:</label>
                <input name="phone" class="easyui-validatebox" validType="integer">
            </div>
            <div class="fitem">
                <label>邮编:</label>
                <input name="email" class="easyui-validatebox" validType="email">
            </div>
            <div class="fitem">
                <input name="userId" style="display: none;">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','New User');
            $('#fm').form('clear');
            url = 'addUser';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','Edit User');
                $('#fm').form('load',row);
                url = 'editUser?id='+row.userId;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                 },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.success==false){
                        $.messager.show({
                            title: 'Error',
                            msg: result.msg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg:result.msg
                        });
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('deleteUser',{id:row.userId},function(result){
                            if (result.success==true){
                                $('#dg').datagrid('reload');    // reload the user data
                                 $.messager.show({
                            			title: 'Success',
                           			  msg:result.msg
                        				  });
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                            				msg: result.msg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    $(function(){
  		$('#fm input').each(function () {
            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        })
 		});
    $.extend($.fn.validatebox.defaults.rules, {
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message: '邮政编码不存在'
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    mobile: {
        validator: function (value, param) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);
        },
        message: '手机号码不正确'
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '密码由字母和数字组成，至少6位'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    },
    integer : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[1-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数' 
    },
    idcard: {
        validator: function (value, param) {
            return idCard(value);
        },
        message:'请输入正确的身份证号码'
    }
});
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
</body>
</html>