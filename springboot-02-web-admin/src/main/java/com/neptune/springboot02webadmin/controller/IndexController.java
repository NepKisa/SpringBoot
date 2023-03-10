package com.neptune.springboot02webadmin.controller;

import com.neptune.springboot02webadmin.bean.City;
import com.neptune.springboot02webadmin.bean.Student;
import com.neptune.springboot02webadmin.bean.User;
import com.neptune.springboot02webadmin.service.impl.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentService studentService;
//
////    @Autowired
//    StringRedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/student")
    public Student student(@RequestParam("id") Long id) {
        return studentService.getStudent(id);
    }

    @ResponseBody
    @GetMapping("/studenta")
    public Student student(@RequestParam("age") Integer age) {

        return studentService.getStudent(age);
    }

    @ResponseBody
    @PostMapping ("/studentb")
    public Student insert(@RequestBody Student student) {
        studentService.insert(student);
        return student;
    }

    @ResponseBody
    @GetMapping("/sql")
    public List<Map<String, Object>> contextLoads() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from student");
        return maps;
    }

    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city) {
        //      cityService.saveCity(city);
        return city;
    }

//    @ResponseBody
//    @GetMapping("/city")
//    public City getCityById(@RequestParam("id") Long id){
//        return cityService.getById(id);
//    }

//    @ResponseBody
//    @GetMapping("/acct")
//    public Account getById(@RequestParam("id") Long id){
//
//        return accountService.getAcctByid(id);
//    }


/*
    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_tbl", Long.class);
        return aLong.toString();
    }
*/

    /**
     * ????????????
     *
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {

        return "login";
    }


    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) { //RedirectAttributes

        if (StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())) {
            //????????????????????????????????????
            session.setAttribute("loginUser", user);
            //????????????????????????main.html;  ?????????????????????????????????
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "??????????????????");
            //??????????????????
            return "login";
        }

    }

    /**
     * ???main??????
     *
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {

        log.info("??????????????????{}", "mainPage");
        //???????????????  ?????????????????????
//        Object loginUser = session.getAttribute("loginUser");
//        if(loginUser != null){
//            return "main";
//        }else {
//            //??????????????????
//            model.addAttribute("msg","???????????????");
//            return "login";
//        }
//        ValueOperations<String, String> opsForValue =
//                redisTemplate.opsForValue();
//
//        String s = opsForValue.get("/main.html");
//        String s1 = opsForValue.get("/sql");
//
//
//        model.addAttribute("mainCount",s);
//        model.addAttribute("sqlCount",s1);

        return "main";

    }
}
