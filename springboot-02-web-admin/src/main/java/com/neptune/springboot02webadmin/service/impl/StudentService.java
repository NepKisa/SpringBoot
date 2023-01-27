package com.neptune.springboot02webadmin.service.impl;

import com.neptune.springboot02webadmin.bean.Student;
import com.neptune.springboot02webadmin.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public Student getStudent(Long id) {
        return studentMapper.selectById(id);
    }

    public Student getStudent(Integer age) {
        return studentMapper.getByAge(age);
    }

    public void insert(Student student){
      studentMapper.insert(student);
    }
}
