package com.ra.crud_user.dao.impl;

import com.ra.crud_user.dao.IUserDao;
import com.ra.crud_user.model.Users;
import com.ra.crud_user.utils.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao
{
    // Statement - PreparedStatement - CallableStatement

    /**
     * Statement - sql tĩnh - không có tham số
     * PreparedStatement - sql động có tham
     * CallableStatement - để gọi procedure
     */

    @Override
    public List<Users> findAll()
    {
        List<Users> list = new ArrayList<>();
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement("select * from users");
        )
        {
            /**
             * execute
             * executeQuery - thực hiện những câu lệnh truy vấn
             * executeUpdate - thực hiện những câu lệnh insert update delete
             * */
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setDateOfBirth(
                        rs.getDate("dateOfBirth").toLocalDate()
                );
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));

                list.add(user);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Users findById(int id)
    {
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
        )
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setDateOfBirth(
                        rs.getDate("dateOfBirth").toLocalDate()
                );
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addNew(Users users)
    {
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement("insert into users (name,dateOfBirth,address,phone) values (?,?,?,?)")
        )
        {
            ps.setString(1, users.getName());
            // nhận vào số milis
            ps.setDate(
                    2,
                    new Date(
                            users.getDateOfBirth()
                                    .atStartOfDay()
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()
                                    .toEpochMilli())
            );
            ps.setString(3, users.getAddress());
            ps.setString(4, users.getPhone());
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUsers(Users users)
    {
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement("update users set name=?,dateOfBirth=?,address=?,phone=? where id=?");
        )
        {
            ps.setString(1, users.getName());
            ps.setDate(
                    2,
                    new Date(
                            users.getDateOfBirth()
                                    .atStartOfDay()
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()
                                    .toEpochMilli())
            );
            ps.setString(3, users.getAddress());
            ps.setString(4, users.getPhone());
            ps.setInt(5, users.getId());
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUsers(int id)
    {
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement("delete from users where id=?");
        )
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
