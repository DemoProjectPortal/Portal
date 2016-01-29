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

import javax.jws.soap.SOAPBinding.Use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.dao.EmploymentDAO;
import com.cisco.dao.QualificationDAO;
import com.cisco.dao.QualificationMasterDAO;
import com.cisco.dao.QualificationMasterDAOImpl;
import com.cisco.dao.UserDAO;
import com.cisco.dao.UserDAOImpl;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private QualificationMasterDAO qulificationMasterDao;
	
	@Autowired
	private QualificationDAO qulificationDao;
	
	@Autowired
	private EmploymentDAO  employmentDAO;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody JsonResponse addDetails(@RequestBody UserJson json) throws ParseException {
		System.out.println(json);
		JsonResponse jsonResponse = new JsonResponse();
		try {
			PersonalDetails pd = json.getPersonalDetails();
			String mobile = pd.getMobileNo();
			List<User> existingUsers = userDao.getUserByMobile(mobile);
			if (existingUsers.size() > 0) {
				logger.error("User with mobile no. : " + mobile
						+ " already exists. Please specify a different mobile number to continue.");
				jsonResponse.setResponseCode(400);
				jsonResponse.setStatus("Error");
				jsonResponse.setDesription("User with mobile no. : " + mobile
						+ " already exists. Please specify a different mobile number to continue.");
				return jsonResponse;
			}
			Register r = json.getRegister();
			Education edu = json.getEducation();
			Employment emp = json.getEmployment(); 
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			
			User user = new User();
			
			user.setFirstName(r.getFirstName());
			user.setLastName(r.getLastName());
			user.setGender(pd.getGender());
			user.setDob(dateFormat.parse(pd.getBirthDate()));
			user.setMobile(mobile);
			user.setEmail(r.getEmail());
			user.setPassword(r.getPassword());
			user.setPermanentAddress(pd.getPermanentAddress());
			user.setCurrentAddress(pd.getCurrentAddress());
			
			Calendar calendar = Calendar.getInstance();
			Date createdDate = new Date(calendar.getTime().getTime());
			
			user.setCreatedDate(createdDate);
		
//		user.setUserName(userName);
//		user.setAdmin(admin);
			
			long userId = userDao.addUser(user);
			System.out.println("User Created!! with id:------");
			System.out.println(userId);
			
////	/**** Add QulificationMaster  *****/		
			QualificationMaster qualificationMaster = new QualificationMaster();
			qualificationMaster.setQualificationName(edu.getQualification());
			long qualificationMasterId = qulificationMasterDao.addQualificationMaster(qualificationMaster);
			
			Qualification qualification = new Qualification();
			
			qualification.setQualificationId(qualificationMasterId); //edu.getQualification()
			qualification.setUser(user);
			qualification.setStartDate(dateFormat.parse(edu.getStartDate()));
			qualification.setEndDate(dateFormat.parse(edu.getEndDate()));
			qualification.setDescription(edu.getQualification());
			qualification.setUniversity(edu.getUniversity());
			qualification.setPercentage(Double.parseDouble(edu.getPercentage()));		
//		qulification.setDescription();
			
			long qualificationId = qulificationDao.addQualification(qualification);
			System.out.println("Qualification added: with id --------------------");
			System.out.println(qualificationId);		
			
			EmployementDetails e = new EmployementDetails();
			
			Set<User> users = new HashSet<User>();
			users.add(user);
			
			e.setUsers(users);
			
			e.setCompanyName(emp.getCompanyName());
			e.setDesignation(emp.getDesignation());
			e.setEndDate(dateFormat.parse(emp.getEndDate()));
			e.setStartDate(dateFormat.parse(emp.getStartDate()));
			
			long emplId = employmentDAO.addEmployment(e);
			System.out.println("Employment Details added: with id --------------------");
			System.out.println(emplId);
			jsonResponse.setResponseCode(200);
			jsonResponse.setStatus("success");
			jsonResponse.setDesription("Registered successfully!");
		} catch (Exception exception) {
			jsonResponse.setResponseCode(400);
			jsonResponse.setStatus("Error");
			jsonResponse.setDesription("Registration failed!");
			logger.error("Error in registration : "+exception.getMessage());
		}	
		
		return jsonResponse;
	}
	
	@RequestMapping(value = "/qualification", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody String qualificationDetails(@RequestBody UserJson json) throws ParseException {
		QualificationMasterDAOImpl qm = new QualificationMasterDAOImpl();
//		QualificationMaster q = qm.listQualificationMaster();
//		System.out.println(q);
		return "";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private @ResponseBody List<User> update() {
		User user = new User();
		user.setFirstName("Sandip");
		userDao.addUser(user);
		System.out.println("Table is created in DB!!");
		// returning array  -->  new User[]{user}
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
		// returning array  -->  new User[]{user}
		List<User> list = new ArrayList<User>();
		list.add(user);
		return list;
	}

}
