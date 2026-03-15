package com.example.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.dto.LoginDTO;
import com.example.student.dto.RegisterDTO;
import com.example.student.entity.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 学生服务实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean register(RegisterDTO registerDTO) {

        // 检查学号是否已存在
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStudentId, registerDTO.getStudentId());
        if (studentMapper.selectOne(wrapper) != null) {
            return false;
        }

        // 创建学生对象
        Student student = new Student();
        BeanUtils.copyProperties(registerDTO, student);
        student.setCreateTime(new Date());
        // 设置默认密码
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword("123456");
        }

        // 保存学生信息
        return studentMapper.insert(student) > 0;
    }

    @Override
    public Student login(LoginDTO loginDTO) {
        // 根据学号和密码查询学生
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStudentId, loginDTO.getStudentId())
                .eq(Student::getPassword, loginDTO.getPassword());
        return studentMapper.selectOne(wrapper);
    }

    @Override
    public IPage<Student> page(int page, int size, String name, String studentId, String phone) {
        // 创建分页对象
        Page<Student> pageInfo = new Page<>(page, size);

        // 构建查询条件
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Student::getName, name);
        }
        if (studentId != null && !studentId.isEmpty()) {
            wrapper.like(Student::getStudentId, studentId);
        }
        if (phone != null && !phone.isEmpty()) {
            wrapper.like(Student::getPhone, phone);
        }

        // 执行分页查询
        return studentMapper.selectPage(pageInfo, wrapper);
    }

    @Override
    public Student getById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public boolean save(Student student) {
        student.setCreateTime(new Date());
        // 设置默认密码
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword("123456");
        }
        return studentMapper.insert(student) > 0;
    }

    @Override
    public boolean updateById(Student student) {
        return studentMapper.updateById(student) > 0;
    }

    @Override
    public boolean removeById(Long id) {
        return studentMapper.deleteById(id) > 0;
    }
}