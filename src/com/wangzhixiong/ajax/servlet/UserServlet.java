package com.wangzhixiong.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.wangzhixiong.ajax.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理servlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet
{
    @Override
    public void init() throws ServletException {
        ArrayList<User> list = new ArrayList<>();
        User user1 = new User("1","zhangsan","123",2000d,"1997-09-01");
        User user2 = new User("2","lisi","123",3000d,"1998-08-23");
        User user3 = new User("3","zhaoliu","123",2500d,"1996-05-16");
        User user4 = new User("4","wangwu","123",2080d,"1995-10-12");
        User user5 = new User("5","zhengsan","123",3200d,"1999-12-20");
        User user6 = new User("6","liugang","123",4200d,"1994-04-10");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);


        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("list",list);
    }


    // 获取页面初始化数据
    private void getData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> list = (List<User>) this.getServletContext().getAttribute("list");
        String s = JSON.toJSONString(list);
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.print(s);
        pw.flush();
        pw.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        if("getData".equals(flag)){
            this.getData(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPost(req, resp);
    }
}
