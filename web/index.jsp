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
      })


      // 初始化用户数据
      function getData(){
        $.getJSON("user.do",{flag:"getData"},function (result){
          listUser(result);
        })
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
                  "<td><a href='#'>更新</a><a href='#'>删除</a></td></tr>"
        })
        $("#tBody").prepend(str);
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
