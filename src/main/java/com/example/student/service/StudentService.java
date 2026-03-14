package com.example.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.dto.LoginDTO;
import com.example.student.dto.RegisterDTO;
import com.example.student.entity.Student;

/**
 * 学生服务接口
 */
public interface StudentService {
    /**
     * 学生注册
     * @param registerDTO 注册信息
     * @return 注册结果
     */
    boolean register(RegisterDTO registerDTO);

    /**
     * 学生登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    Student login(LoginDTO loginDTO);

    /**
     * 学生分页查询
     * @param page 页码
     * @param size 每页大小
     * @param name 姓名模糊查询
     * @param studentId 学号模糊查询
     * @param phone 手机号模糊查询
     * @return 分页结果
     */
    IPage<Student> page(int page, int size, String name, String studentId, String phone);

    /**
     * 根据ID获取学生信息
     * @param id 学生ID
     * @return 学生信息
     */
    Student getById(Long id);

    /**
     * 新增学生
     * @param student 学生信息
     * @return 新增结果
     */
    boolean save(Student student);

    /**
     * 更新学生信息
     * @param student 学生信息
     * @return 更新结果
     */
    boolean updateById(Student student);

    /**
     * 删除学生
     * @param id 学生ID
     * @return 删除结果
     */
    boolean removeById(Long id);
}