package com.goit;

import com.goit.dto.*;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseQueryService {
   private static Database database;

   public DatabaseQueryService() throws SQLException {
       database = Database.getInstance();
   }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
        ResultSet resultSet = database.executeQuery(fileReader("find_max_projects_client.sql"));
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        List<MaxProjectCountClient> list = new ArrayList<>();
        while (resultSet.next()){
            for (int i = 1; i < columnCount; i=i+2) {
                String columnName = resultSetMetaData.getColumnName(i);
                String columnCountProjects = resultSetMetaData.getColumnName(i+1);
                String valueName = resultSet.getObject(columnName).toString();
                int valueCuntProjects = Integer.parseInt(resultSet.getObject(columnCountProjects).toString());
                list.add(new MaxProjectCountClient(valueName,valueCuntProjects));
            }
        }
        return list;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException{
        ResultSet resultSet = database.executeQuery(fileReader("find_max_salary_worker.sql"));
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        List<MaxSalaryWorker> list = new ArrayList<>();
        while (resultSet.next()){
            for (int i = 1; i < columnCount; i=i+5) {
                String columnId = resultSetMetaData.getColumnName(i);
                String columnName = resultSetMetaData.getColumnName(i+1);
                String columnBirthday = resultSetMetaData.getColumnName(i+2);
                String columnLevel = resultSetMetaData.getColumnName(i+3);
                String columnSalary= resultSetMetaData.getColumnName(i+4);

                int valueId = Integer.parseInt(resultSet.getObject(columnId).toString());
                String valueName = resultSet.getObject(columnName).toString();
                String valueBirthday = resultSet.getObject(columnBirthday).toString();
                String valueLevel = resultSet.getObject(columnLevel).toString();
                int valueSalary = Integer.parseInt(resultSet.getObject(columnSalary).toString());
                list.add(new MaxSalaryWorker(valueId, valueName, valueBirthday,
                        valueLevel, valueSalary));
            }
        }
        return list;
    }

    public List<LongestProject> findLongestProject() throws SQLException {
        ResultSet resultSet = database.executeQuery(fileReader("find_longest_project.sql"));
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        List<LongestProject> list = new ArrayList<>();
        while (resultSet.next()){
            for (int i = 1; i < columnCount; i=i+2) {
                String columnId = resultSetMetaData.getColumnName(i);
                String columnDuration = resultSetMetaData.getColumnName(i+1);
                int valueId = Integer.parseInt(resultSet.getObject(columnId).toString());
                int valueDuration = Integer.parseInt(resultSet.getObject(columnDuration).toString());
                list.add(new LongestProject(valueId,valueDuration));
            }
        }
        return list;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorker() throws SQLException {
        ResultSet resultSet = database.executeQuery(fileReader("find_youngest_eldest_workers.sql"));
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        List<YoungestEldestWorker> list = new ArrayList<>();
        while (resultSet.next()){
            for (int i = 1; i < columnCount; i=i+3) {
                String columnType = resultSetMetaData.getColumnName(i);
                String columnName = resultSetMetaData.getColumnName(i+1);
                String columnBirthday = resultSetMetaData.getColumnName(i+2);

                String valueType = resultSet.getObject(columnType).toString();
                Type type;
                if(valueType.equalsIgnoreCase("youngest")){
                    type = Type.YOUNGEST;
                }else type = Type.ELDEST;
                String valueName = resultSet.getObject(columnName).toString();
                String valueBirthday = resultSet.getObject(columnBirthday).toString();
                list.add(new YoungestEldestWorker(type, valueName,valueBirthday));
            }
        }
        return list;
    }

    private String fileReader(String filename){
        InputStream inputStream = DatabaseQueryService.class.getClassLoader()
                .getResourceAsStream("sql/"+filename);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()+" ");
        }
        return sb.toString();
    }
}
