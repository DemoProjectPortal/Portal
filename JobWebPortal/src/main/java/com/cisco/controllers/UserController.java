package com.cisco.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.dao.EmploymentDAO;
import com.cisco.dao.QualificationDAO;
import com.cisco.dao.QualificationMasterDAO;
import com.cisco.dao.UserDAO;
import com.cisco.models.Education;
import com.cisco.models.EmployementDetails;
import com.cisco.models.Employment;
import com.cisco.models.JsonResponse;
import com.cisco.models.PersonalDetails;
import com.cisco.models.Qualification;
import com.cisco.models.QualificationMaster;
import com.cisco.models.Register;
//import com.cisco.models.QulificationMaster;
import com.cisco.models.User;
import com.cisco.models.UserJson;

@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserDAO userDao;

  @Autowired
  private QualificationMasterDAO qulificationMasterDao;

  @Autowired
  private QualificationDAO qulificationDao;

  @Autowired
  private EmploymentDAO employmentDAO;

  @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json")
  private @ResponseBody JsonResponse addDetails(@RequestBody UserJson json) throws ParseException {
    System.out.println(json);
    JsonResponse response = new JsonResponse();
    try {
      PersonalDetails personalDetails = json.getPersonalDetails();
      Register register = json.getRegister();
      Education education = json.getEducation();
      Employment employment = json.getEmployment();

      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

      // Populate user data
      User user = new User();
      user.setFirstName(register.getFirstName());
      user.setLastName(register.getLastName());
      user.setEmail(register.getEmail());
      user.setPassword(register.getPassword());
      user.setGender(personalDetails.getGender());
      user.setDob(dateFormat.parse(personalDetails.getBirthDate()));
      user.setMobile(personalDetails.getMobileNo());
      user.setPermanentAddress(personalDetails.getPermanentAddress());
      user.setCurrentAddress(personalDetails.getCurrentAddress());
      Calendar calendar = Calendar.getInstance();
      Date createdDate = new Date(calendar.getTime().getTime());
      user.setCreatedDate(createdDate);
      userDao.addUser(user);

      // Add qualifications
      Qualification qualification = new Qualification();
      qualification.setQualificationId(Integer.parseInt(education.getQualification()));
      qualification.setUser(user);
      qualification.setStartDate(dateFormat.parse(education.getStartDate()));
      qualification.setEndDate(dateFormat.parse(education.getEndDate()));
      qualification.setDescription(education.getSpecialization());
      qualification.setUniversity(education.getUniversity());
      qualification.setPercentage(Double.parseDouble(education.getPercentage()));
      qulificationDao.addQualification(qualification);

      // Populate employment details
      EmployementDetails employmentDetails = new EmployementDetails();

      Set<User> users = new HashSet<User>();
      users.add(user);
      employmentDetails.setUsers(users);
      employmentDetails.setCompanyName(employment.getCompanyName());
      employmentDetails.setDesignation(employment.getDesignation());
      employmentDetails.setEndDate(dateFormat.parse(employment.getEndDate()));
      employmentDetails.setStartDate(dateFormat.parse(employment.getStartDate()));
      employmentDAO.addEmployment(employmentDetails);

      response.setResponseCode(200);
      response.setStatus("success");
      response.setDesription("Registered successfully!");
    } catch (Exception e) {
      response.setResponseCode(400);
      response.setStatus("Error");
      response.setDesription("Registration failed!");
      e.printStackTrace();
    }
    return response;
  }

  @RequestMapping(value = "/validateEmail", method = RequestMethod.POST, produces = "application/json")
  private @ResponseBody JsonResponse validateEmail(@RequestBody UserJson json)
      throws ParseException {
    List<User> userData = null;
    String status = "";
    try {
      System.out.println(json.getRegister().getEmail());
      userData = userDao.getUserById(json.getRegister().getEmail());
      status = "success";
    } catch (Exception e) {
      status = "error";
      e.printStackTrace();
    }
    JsonResponse response = new JsonResponse();
    if (userData.size() <= 0) {
      response.setResponseCode(200);
      response.setStatus(status);
      response.setDesription("Email is valid");
    } else {
      response.setResponseCode(400);
      response.setStatus("Error");
      response.setDesription("Email already exits");
    }

    return response;
  }

  @RequestMapping(value = "/qualification", method = RequestMethod.GET, produces = "application/json")
  private @ResponseBody String qualificationDetails(@RequestBody UserJson json)
      throws ParseException {
    // QualificationMaster q = qm.listQualificationMaster();
    // System.out.println(q);
    return "";
  }

  /**
   * This method gets the list of available qualifications from database and
   * returns a JsonResponse object.
   * 
   * @return Object
   * @throws ParseException
   */
  @RequestMapping(value = "/getQualificationList", method = RequestMethod.GET, produces = "application/json")
  private @ResponseBody JsonResponse getQualificationList() throws ParseException {
    List<QualificationMaster> listOfQualifications = qulificationMasterDao
        .getListOfQualificationMaster();
    JsonResponse response = new JsonResponse();
    response.setResponseCode(200);
    response.setStatus("success");
    response.setData(listOfQualifications);
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  private @ResponseBody List<User> update() {
    User user = new User();
    user.setFirstName("Sandip");
    userDao.addUser(user);
    System.out.println("Table is created in DB!!");
    // returning array --> new User[]{user}
    List<User> list = new ArrayList<User>();
    list.add(user);
    return list;
  }

  @RequestMapping("/delete")
  private @ResponseBody List<User> delete() {
    User user = new User();
    user.setFirstName("Sandip");
    userDao.addUser(user);
    System.out.println("Test ");
    // returning array --> new User[]{user}
    List<User> list = new ArrayList<User>();
    list.add(user);
    return list;
  }

}
