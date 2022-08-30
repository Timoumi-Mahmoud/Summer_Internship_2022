package com.internship.internship.controller;


import com.internship.internship.entity.Department;
import com.internship.internship.entity.Role;
import com.internship.internship.entity.User;
import com.internship.internship.repository.FunctionRepository;
import com.internship.internship.repository.RoleRepository;
import com.internship.internship.services.DepartmentService;
import com.internship.internship.services.FunctionService;
import com.internship.internship.services.RoleService;
import com.internship.internship.services.UserService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/User")
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private  RoleService roleService;
  @Autowired
  private DepartmentService departmentService;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private FunctionService functionService;

/*
public UserController(UserService UserService){  same as autowired
}*/

    @RequestMapping(value = "/list/{id}")
    public User findById(@PathVariable int id) {
        return userService.findBy(id);
    }



    @GetMapping("delete/{id}")
    public RedirectView  remove( @PathVariable int id){
        userService.delete(id);
        return new RedirectView("/User/userList");    }

////////////////////////////////////////////////////

    @GetMapping( path={"/userList","/search"})
    public ModelAndView getAllPages(String keyword){
        Map<String, List<User>> model = new HashMap<String, List<User>>();

        String viewName = "/user/userList";
        if(keyword != null){
            model.put("Users",userService.search(keyword));
            return new ModelAndView(viewName , model);
        }

        return getOnePage( 1);
    }


    @GetMapping("/userList/page/{pageNumber}")
    public ModelAndView getOnePage( @PathVariable("pageNumber") int currentPage){
        Page<User> page = userService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<User> Users = page.getContent();
        ModelAndView mav = new ModelAndView("/user/userList");

        mav.addObject("currentPage", currentPage);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("Users", Users);

        return mav;
    }

    @GetMapping("/addUser")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("user/registration");
        mav.addObject("departs", departmentService.findAll());
        mav.addObject("listRoles", roleService.findAll());
        User user = new User();
        mav.addObject("user", user);
        Map<String, List<Department>> departs = new HashMap<String, List<Department>>();
        mav.addObject("Function", functionService.findAll());

        return mav;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public ModelAndView  saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("user/registration");

            mav.addObject("departs", departmentService.findAll());
            mav.addObject("listRoles", roleService.findAll());
       //     mav.addObject("functionId", functionService.findBy(id));
          //  return new ModelAndView("/user/registration");
            return mav;
        }
        userService.save(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/User/userList");

        return new ModelAndView(redirectView);
    }


    /////////////////////////////////////////////////////////////////////////
    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") int id) {
        User user = userService.findBy(id);

        ModelAndView mav = new ModelAndView("role_user/giveRole");
        mav.addObject("user", user);
        mav.addObject("u", userService.findBy(id));
        List<Role> listRoles = roleService.findAll();
        mav.addObject("listRoles", listRoles);
        return mav;
    }
    @RequestMapping(value ="/edit/{id}" , method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid @ModelAttribute("user") User user, @PathVariable("id") int id) {

        userService.update(user, id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);
    }
    /////////////////////////////////////////////////////////////////////////
    @GetMapping("/login")
    public ModelAndView  loginPage() {
        ModelAndView mav = new ModelAndView("user/login");
        return mav;
    }



















    //////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/e/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Role role = roleService.findBy(id);
        ModelAndView mav = new ModelAndView("role/update");
        mav.addObject("role", role);
        mav.addObject("Function", functionService.findAll());

        return mav;
    }
    @PostMapping(value = "/e/{id}")
    public ModelAndView updateSave(@PathVariable("id") int id, Role role ) {
        role.setId(id);
        roleRepository.save(role);
        ModelAndView mav = new ModelAndView("role/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Role/listRole");
        return new ModelAndView(redirectView);
    }

//////////////////////////////////////////////////////////////////////////////////







    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return userService.exportReport(format);
    }
    @Autowired
    ApplicationContext context;
    @GetMapping(path = "/pdf")
    @ResponseBody
//    public void getPdf(@PathVariable String jrxml, HttpServletResponse response) throws Exception {
    public void getPdf(HttpServletResponse response) throws Exception {
        //Get JRXML template from resources folder
//        Resource resource = context.getResource("classpath:reports/" + jrxml + ".jrxml");
        Resource resource = context.getResource("classpath:userDetails.jrxml");

        //Compile to jasperReport
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        //Parameters Set
        Map<String, Object> params = new HashMap<>();

        List<Map<String, Object>> users = (List<Map<String, Object>>) userService.getAllUsers();

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(users);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


































































}
