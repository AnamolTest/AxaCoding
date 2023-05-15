package jp.co.axa.apidemo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;

 



 
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
	
	@InjectMocks
    EmployeeController employeeController;

		
	@Mock
    EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;
    
      
    @Test
    public void testAddEmployee() throws JsonProcessingException, Exception
    {
        
    	Employee emp=new Employee(100l,"Anamol",14000,"IT");
    	when(employeeService.saveEmployee(emp)).thenReturn(emp);
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	mvc.perform(MockMvcRequestBuilders.
    			post("/api/v1/employees")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.content(new ObjectMapper().writeValueAsString(emp)))
    			.andExpect(status().isOk());
    
    }
    
    @Test
    public void getEmployeeById() throws JsonProcessingException, Exception
    {
        
    	Employee emp=new Employee(1l,"Anamol",14000,"IT");
    	employeeService.saveEmployee(emp);
    	
    	when(employeeService.getEmployee(1l)).thenReturn(emp);
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	mvc.perform(MockMvcRequestBuilders.
    			get("/api/v1/employees/{employeeId}",1l)
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))    			 
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.name").value("Anamol"));
    
    }
    
    
    @BeforeEach
    void initUseCase() {
    	
    	Employee e1=new Employee(300l,"Anamol3",14000,"IT");
    	Employee e2=new Employee(400l,"Anamol4",16000,"CONSULTING");
    	
    	List<Employee> emplst=new ArrayList<>();
    	emplst.add(e1);
    	emplst.add(e2);  	 
    	
    	employeeRepository.saveAll(emplst);
    	
    }
    
    @Test  
    @Transactional
    public void retrieveEmployee() throws JsonProcessingException, Exception
    {
    	
    	
    	Employee e1=new Employee(300l,"Anamol",14000,"IT");
    	Employee e2=new Employee(40l,"Anamol",14000,"IT");
    	
    	List<Employee> emplst=new ArrayList<>();
    	emplst.add(e1);
    	emplst.add(e2);  	 
    	
    	//employeeRepository.saveAll(emplst);
    	
    	
    	
    	when(employeeService.retrieveEmployees()).thenReturn(emplst);
    	when(employeeRepository.findAll()).thenReturn(emplst); 	
    	
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	
    	mvc.perform(MockMvcRequestBuilders.
    			get("/api/v1/employees")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))    				 
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$[0].name").value("Anamol"));
    
    }
    
    @Test 
    @Transactional
    public void deleteEmployee() throws JsonProcessingException, Exception
    {
    	Employee e1=new Employee(300l,"Anamol",14000,"IT");
    	employeeService.saveEmployee(e1);
    	
    	doNothing().when(employeeService).deleteEmployee(1l);
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	mvc.perform(MockMvcRequestBuilders.
    			delete("/api/v1/employees/{employeeId}",1l)
    			.contentType(MediaType.APPLICATION_JSON)    		 
    			.content(new ObjectMapper().writeValueAsString(e1)))
    			.andExpect(status().isOk());
    		
    }
    }

	