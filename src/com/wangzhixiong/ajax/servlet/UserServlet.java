package com.wangzhixiong.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.wangzhixiong.ajax.pojo.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        }else if ("addUser".equals(flag)){
            addUser(req,resp);
        }else if ("updateUser".equals(flag)){
            updateUser(req,resp);
        }else if ("delete".equals(flag)){
            deleteUser(req,resp);
        }
    }

    /**
     * 删除指定用户
     * @param req
     * @param resp
     */
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        ServletContext servletContext = getServletContext();
        List<User> list = (List<User>) servletContext.getAttribute("list");
        String userid = req.getParameter("userid");

        // 把list转成map
        Map<String, User> userMap = list.stream().collect(Collectors.toMap(User::getId, Function.identity()));

        if(StringUtils.isNotEmpty(userid)){
            User user = userMap.get(userid);
            list.remove(user);
        }

        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("删除成功");
        writer.flush();
        writer.close();

    }

    /**
     * 更新用户
     * @param req
     * @param resp
     */
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        User user = this.createUser(req);
        ServletContext servletContext = getServletContext();
        List<User> list = (List<User>)servletContext.getAttribute("list");

        // 把list转成map
        Map<String, User> userMap = list.stream().collect(Collectors.toMap(User::getId, Function.identity()));

        User user1 = userMap.get(user.getId());
        list.remove(user1);
        list.add(user);
        list.sort(new Comparator<User>()
        {
            @Override
            public int compare(User o1, User o2)
            {
                return Integer.parseInt(o1.getId() )- Integer.parseInt(o2.getId());
            }
        });
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("更新成功");
        writer.flush();
        writer.close();
    }

    /**
     * 添加用户
     * @param req
     * @param resp
     */
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        req.setCharacterEncoding("utf-8");
        User user = this.createUser(req);
        ServletContext servletContext = this.getServletContext();
        List<User> list = (List<User>)servletContext.getAttribute("list");
        list.add(user);
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("添加成功");
        writer.flush();
        writer.close();

    }


    private User createUser(HttpServletRequest req)
    {
        String userid = req.getParameter("userid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String salary = req.getParameter("salary");
        String birthday = req.getParameter("birthday");

        User user = new User();
        user.setId(userid);
        user.setUserName(username);
        user.setBirthday(birthday);
        user.setSalary(Double.parseDouble(salary));
        user.setBirthday(birthday);
        user.setPassword(password);
        return user;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPost(req, resp);
    }
}
