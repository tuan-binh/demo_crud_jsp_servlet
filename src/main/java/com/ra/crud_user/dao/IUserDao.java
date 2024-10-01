package com.ra.crud_user.dao;

import com.ra.crud_user.model.Users;

import java.util.List;

public interface IUserDao
{
    /**
     * hiển thị - select * from users
     * tìm kiếm người dùng theo id: select * from users where id = ? -> lúc edit thì lấy ra đối tượng để hiển thị lên form
     * thêm mới - insert into users (name,dateOfBirth,address,phone) values ()
     * cập nhật - update users set name = , dateOfbirth = , ... where id = ?
     * xóa - delete from users where id = ?
     *
     * optional -> tìm kiếm
     * optional about dương -> phân trang sắp xếp.
     * */

    List<Users> findAll();

    Users findById(int id);

    boolean addNew(Users users);

    boolean updateUsers(Users users);

    void deleteUsers(int id);
}
