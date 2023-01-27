package com.neptune.springboot02webadmin.mapper;

import com.neptune.springboot02webadmin.bean.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("select * from student where age=#{age}")
     Student getByAge(Integer age);

     Student selectById(Long id);

     @Insert("insert into student (`id`,`name`,`age`) values (#{id},#{name},#{age})")
//     @Options(useGeneratedKeys=true,keyProperty = "id")
     void insert(Student student);
}
