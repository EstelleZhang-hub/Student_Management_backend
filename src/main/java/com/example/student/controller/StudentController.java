package com.example.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.student.dto.LoginDTO;
import com.example.student.dto.RegisterDTO;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import com.example.student.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生控制器
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 学生注册
     * @param registerDTO 注册信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterDTO registerDTO) {
        boolean result = studentService.register(registerDTO);
        if (result) {
            return Result.success(null);
        } else {
            return Result.fail(400, "学号已存在");
        }
    }

    /**
     * 学生登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDTO loginDTO) {
        Student student = studentService.login(loginDTO);
        if (student != null) {
            return Result.success(student);
        } else {
            return Result.fail(400, "学号或密码错误");
        }
    }

    /**
     * 学生分页查询
     * @param page 页码
     * @param size 每页大小
     * @param name 姓名模糊查询
     * @param studentId 学号模糊查询
     * @param phone 手机号模糊查询
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<?> page(@RequestParam(defaultValue = "1") int page, 
                         @RequestParam(defaultValue = "10") int size, 
                         @RequestParam(required = false) String name, 
                         @RequestParam(required = false) String studentId, 
                         @RequestParam(required = false) String phone) {
        IPage<Student> pageResult = studentService.page(page, size, name, studentId, phone);
        return Result.success(pageResult);
    }

    /**
     * 根据ID获取学生信息
     * @param id 学生ID
     * @return 学生信息
     */
    @GetMapping("/get/{id}")
    public Result<?> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 新增学生
     * @param student 学生信息
     * @return 新增结果
     */
    @PostMapping("/save")
    public Result<?> save(@RequestBody Student student) {
        boolean result = studentService.save(student);
        if (result) {
            return Result.success(null);
        } else {
            return Result.fail(400, "新增失败");
        }
    }

    /**
     * 更新学生信息
     * @param student 学生信息
     * @return 更新结果
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody Student student) {
        boolean result = studentService.updateById(student);
        if (result) {
            return Result.success(null);
        } else {
            return Result.fail(400, "更新失败");
        }
    }

    /**
     * 删除学生
     * @param id 学生ID
     * @return 删除结果
     */
    @PostMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        boolean result = studentService.removeById(id);
        if (result) {
            return Result.success(null);
        } else {
            return Result.fail(400, "删除失败");
        }
    }
}