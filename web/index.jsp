<%--
  Created by IntelliJ IDEA.
  User: wangzhixiong
  Date: 2023/8/13
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户管理</title>
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script>
      $(function (){
         // 初始化用户数据
        getData();

        // 添加用户
        $("#add").click(function (){
          addOrUpdateUser("addUser");
        })

        // 更新用户
        $("#update").click(function (){
          addOrUpdateUser("updateUser");
        })

      })


      // 获取页面输入的用户数据
      function addOrUpdateUser(flag){
        var userid = $("#id").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var salary = $("#salary").val();
        var birthday = $("#birthday").val();

        var data = {
          userid : userid,
          username : username,
          password : password,
          salary : salary,
          birthday : birthday,
          flag : flag
        };

        $.get("user.do",data,function (result){
          alert(result);
          location.reload();
        })
      }


      // 初始化用户数据
      function getData(){
        $.getJSON("user.do",{flag:"getData"},function (result){
          listUser(result);
        })
      }

      // 把选择要更新的数据带到上方的table表格中
      function preUpdateUser(userid){
         var arr = new Array();
         //m 遍历选中行的用户数据
         $('#'+userid).closest("tr").children().each(function (index,ele){
           if(index <= 4){
             arr[index] = ele.innerText;
           }
         })
         $("#id").val(arr[0]);
         $("#id").attr("readonly",true);
         $("#username").val(arr[1]);
         $("#password").val(arr[2]);
         $("#salary").val(arr[3]);
         $("#birthday").val(arr[4]);
      }

      // 遍历用户数据，拼接成HTML
      function listUser(obj){
        var str = "";
        $.each(obj,function (){
          str += "<tr align='center'>" +
                  "<td id='"+this.id+"'>"+ this.id +"</td>" +
                  "<td id='"+this.userName+"'>"+ this.userName +"</td>" +
                  "<td id='"+this.password+"'>"+ this.password +"</td>" +
                  "<td id='"+this.salary+"'>"+ this.salary +"</td>" +
                  "<td id='"+this.birthday+"'>"+ this.birthday +"</td>" +
                  "<td><a href='#' onclick='preUpdateUser("+this.id+")'>更新</a>&nbsp;&nbsp;<a href='#' onclick='deleteUser("+this.id+")'>删除</a></td></tr>"
        })
        $("#tBody").prepend(str);
      }


      // 删除指定用户
      function deleteUser(userid){
        // $("#"+userid).closest("tr").remove();
        $.post("user.do",{userid:userid,flag:"delete"},function (result){
          alert(result);
        })
        // getData();
        location.reload();

      }
    </script>


  </head>
  <body>
  <div>
    <table align="center" width="60%" border="1">
      <tr>
        <td>ID：</td>
        <td><input type="text" name="id" id="id"/></td>
        <td>姓名：</td>
        <td><input type="text" name="username" id="username"/></td>
        <td>密码：</td>
        <td><input type="text" name="password" id="password"/></td>
      </tr>
      <tr>
        <td>收入：</td>
        <td><input type="text" name="salary" id="salary"/></td>
        <td>出生日期：</td>
        <td><input type="text" name="birthday" id="birthday"/></td>
        <td colspan="2"></td>
      </tr>
      <tr align="center">
        <td colspan="6">
          <input type="button" value="添加用户" id="add" />
          <input type="button" value="更新用户" id="update"/>
        </td>
      </tr>
    </table> <hr/>
    <table align="center" width="60%" bgcolor="" border="1" id="myTable">
      <thead>
      <tr align="center">
        <td>ID</td>
        <td>姓名</td>
        <td>密码</td>
        <td>收入</td>
        <td>生日</td>
        <td>操作</td>
      </tr>
      </thead>
      <tbody id="tBody"></tbody>
    </table>
  </div>
  </body>

</html>
