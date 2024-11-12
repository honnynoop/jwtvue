package com.ssafy.edu.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.edu.employee.model.Employees;




public class HttpGetRequestMethodExample<ModelMapper> {
	

    public static void main(String[] args) throws IOException {
        //getUsers();
        //getUserById();
    	getEmployeeWithPage(20,0);
        //getEmployeeWithPage(20,20);
        getEmplyees();
        System.out.println("--------------------");
        //getEmpls();
    }
    private static void getEmpls() {
    	  WebClient webClient = WebClient.create();

          // Retrieve the response from the API as a List of DTOs or raw data structure
          List<EmployeeDTO> employeeDTOs = webClient.get()
              .uri("http://localhost:8080/hrms/api/employees/")
              .retrieve()
              .bodyToMono(new ParameterizedTypeReference<List<EmployeeDTO>>() {}) // Fetch the data as DTOs or raw format
              .block();
          /*
          Employees(Integer employeeId, String firstName, String lastName, String email, String phoneNumber,
      			Date hireDate, String jobId, BigDecimal salary, BigDecimal commissionPct, Integer managerId,
      			Integer departmentId)
      			*/
          // Use ModelMapper to map the list of DTOs to domain objects (Employees)
          List<Employees> employees = employeeDTOs.stream()
        		    .map(dto -> new Employees(dto) ) // Ensure types match
        		    .collect(Collectors.toList());

          // Print the mapped list of Employees
          employees.forEach(System.out::println);
    }
    private static void getEmplyees() {
    	WebClient webClient = WebClient.create();

        List<Employees> employees = webClient.get()
            .uri("http://localhost:8080/hrms/api/employees/")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Employees>>() {}) // Correct type reference for List<Employees>
            .block(); // Block to wait for the result (not recommended in production)

        // Do something with the employees list
        employees.forEach(System.out::println);
    }
    private static void getUsers() throws ClientProtocolException, IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("http://localhost:8080/hrms/api/employees/");
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler < String > responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            ObjectMapper objectMapper=new ObjectMapper();
            List<Employees> object = objectMapper.readValue(responseBody, new TypeReference<>() {});
            System.out.println("----------------------------------------"+object.size());
            int i=1;
            for (Employees ee: object) {
				System.out.println((i++)+"    "+ee);
			}
        }
    }
    //@GetMapping("/page/{limit}/{offset}")
    private static void getEmployeeWithPage(int limit, int offset) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("http://localhost:8080/hrms/api/employees/pagewt/"+limit+"/"+offset);
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler < String > responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            ObjectMapper objectMapper=new ObjectMapper();
            List<Employees> object = objectMapper.readValue(responseBody, new TypeReference<>() {});
            System.out.println("----------------------------------------"+object.size());
            int i=1;
            for (Employees ee: object) {
				System.out.println((i++)+"    "+ee);
			}
        }
    }
    
    private static void getUserById() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("http://localhost:8080/hrms/api/employees/100");
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler < String > responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            ObjectMapper objectMapper=new ObjectMapper();
            Employees object = objectMapper.readValue(responseBody, Employees.class);
            System.out.println("----------------------------------------"+object);
        }
    }
    
}