package com.ra.crud_user;

import com.ra.crud_user.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/user-servlet")
public class UserServlet extends HttpServlet
{

    public static List<Users> usersList = new ArrayList<>();

    @Override
    public void init() throws ServletException
    {
        usersList.add(new Users(1, "Võ Hoàng Yến", LocalDate.now(), "Hà Nội", "0987654321"));
        usersList.add(new Users(2, "Lương Sơn Bá", LocalDate.now(), "Hà Nội", "0987654321"));
        usersList.add(new Users(3, "Trúc Anh Đài", LocalDate.now(), "Hà Nội", "0987654321"));
        usersList.add(new Users(4, "Mã Văn Tài", LocalDate.now(), "Hà Nội", "0987654321"));
        usersList.add(new Users(5, "Kim Bình Mai", LocalDate.now(), "Hà Nội", "0987654321"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Làm để để phân biệt được lúc hiển thị và điều hướng sang trang add
        // parameter là tham số thì có key - value
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        System.out.println("action = " + action);

        switch (action)
        {
            case "add":
            {
                req.getRequestDispatcher("/WEB-INF/add.jsp").forward(req, resp);
                break;
            }
            case "edit":
            {
                int id = Integer.parseInt(req.getParameter("id"));

                Users user = usersList.stream()
                        .filter(u -> u.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (user != null)
                {
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
                }
                break;
            }
            case "delete":
            {
                int id = Integer.parseInt(req.getParameter("id"));

                Users user = usersList.stream()
                        .filter(u -> u.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (user != null)
                {
                    usersList.remove(user);
                }
                showListUsers(req, resp);
                break;
            }
            default:
                showListUsers(req, resp);
        }
    }

    private void showListUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // gửi dữ liệu sang trang jsp
        req.setAttribute("usersList", usersList);
        // điều hướng sang trang
        req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action)
        {
            case "add":
            {
                String name = req.getParameter("name");
                String dob = req.getParameter("dob");
                LocalDate dateOfBirth = LocalDate.parse(dob, dtf);
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");

                Users user = new Users(getNewId(), name, dateOfBirth, address, phone);
                usersList.add(user);
                // cách 1: gọi lại hàm
//                showListUsers(req, resp);
                // cách 2: sendRedirect() - gọi lại đường dẫn;
                resp.sendRedirect(req.getContextPath() + "/user-servlet");
                break;
            }
            case "update":
            {
                int id = Integer.parseInt(req.getParameter("id"));

                String name = req.getParameter("name");
                String dob = req.getParameter("dob");
                LocalDate dateOfBirth = LocalDate.parse(dob, dtf);
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                Users user = new Users(id, name, dateOfBirth, address, phone);

                int indexUpdate = usersList.stream().map(Users::getId).toList().indexOf(id);

                usersList.set(indexUpdate, user);
                showListUsers(req, resp);
                break;
            }
            default:
                // gửi dữ liệu sang trang jsp
                showListUsers(req, resp);
        }
    }

    public int getNewId()
    {
        Optional<Users> users = usersList.stream().max(Comparator.comparingInt(Users::getId));
        return users.map(item -> item.getId() + 1).orElse(1);
    }

}
