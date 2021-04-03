package com.util;

import com.comparator.EmployeeSalaryComparator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Employee;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class EmployeeUtil {
	
	//Input File Path
    public static final String jsonFilePath = "src/main/resources/employee.json";
    public static final String excelFilePath = "src/main/resources/employee.csv";
    
    
    //Generate Unique Values From JSON and CSV

    public static void GenerateUniqueValuesFromJsonCsv(){
    	
    	
    	Set<Employee> uniqueEmployees = new HashSet<Employee>();
    	
    	//Parsing JSON to Java  Object
        List<Employee> jsonEmployeeList = convertToObjectFromJson(jsonFilePath);
      //Parsing CSV to Java  Object
        List<Employee> excelEmployeeList = convertToObjectFromExcel(excelFilePath);
        uniqueEmployees.addAll(excelEmployeeList);
        uniqueEmployees.addAll(jsonEmployeeList);
        System.out.println("uniqueEmployees ::"+uniqueEmployees.size());
        List<Employee> employeeList = new ArrayList<>(uniqueEmployees);
        Collections.sort(employeeList, new EmployeeSalaryComparator());
        
        //Parsing  Java Object to CSV
        convertToExcelFromObject(employeeList);
    	
    }

    
    
  //Generate  CSV from Java Object
    private static void convertToExcelFromObject(List<Employee> jsonEmployeeList) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Employee Data");// creating a blank sheet
            int rowNum = 1;
            Row rowHeader = sheet.createRow(0);
            createHeader(workbook, rowHeader);
            for (Employee employee : jsonEmployeeList) {
                Row row = sheet.createRow(rowNum++);
                createCellDataList(employee, row);
            }
            //Output CSV file Path
            FileOutputStream out = new FileOutputStream(new File("Employee_data_export.csv")); // file name with path
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    
    //Create Header in CSV file
    private static void createHeader(XSSFWorkbook workbook , Row rowHeader) {
        XSSFFont my_font = workbook.createFont();
        XSSFCellStyle my_style = workbook.createCellStyle();
        my_font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        my_style.setFont(my_font);
        Cell cell = rowHeader.createCell(0);
        cell.setCellStyle(my_style);
        cell.setCellValue("First Name");
        cell = rowHeader.createCell(1);
        cell.setCellStyle(my_style);
        cell.setCellValue("Last Name");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(my_style);
        cell.setCellValue("Age");
        cell = rowHeader.createCell(3);
        cell.setCellStyle(my_style);
        cell.setCellValue("Address");
        cell = rowHeader.createCell(4);
        cell.setCellStyle(my_style);
        cell.setCellValue("Salary");

    }
    
    //Inserting Values in the Row

    private static void createCellDataList(Employee employee, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(employee.getFirstName());
        cell = row.createCell(1);
        cell.setCellValue(employee.getLastName());
        cell = row.createCell(2);
        cell.setCellValue(employee.getAge());
        cell = row.createCell(3);
        cell.setCellValue(employee.getAddress());
        cell = row.createCell(4);
        cell.setCellValue(employee.getSalary());
    }

    
    //Parsing CSV to Java  Object
    private static List<Employee> convertToObjectFromExcel(String jsonFilePath) {
        HSSFWorkbook workbook = null;
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            workbook = new HSSFWorkbook(new FileInputStream(new File(jsonFilePath)));
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Employee employee = new Employee();
                Row ro = sheet.getRow(i);
                for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
                    Cell ce = ro.getCell(j);
                    if (j == 0) {
                        employee.setFirstName(ce.getStringCellValue());
                    }
                    if (j == 1) {
                        employee.setLastName(ce.getStringCellValue());
                    }
                    if (j == 2) {
                        employee.setAge((int) ce.getNumericCellValue());
                    }
                    if (j == 3) {
                        employee.setAddress(ce.getStringCellValue());
                    }
                    if (j == 4) {
                        employee.setSalary(ce.getNumericCellValue());
                    }
                }
                employeeList.add(employee);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }
    
    
  //Parsing JSON to Java  Object

    private static List<Employee> convertToObjectFromJson(String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employees = null;
        File file = new File(jsonFilePath);
        try {
            employees = objectMapper.readValue(file, new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
