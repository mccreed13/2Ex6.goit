package com.goit;

import com.goit.dto.LongestProject;
import com.goit.dto.MaxProjectCountClient;
import com.goit.dto.MaxSalaryWorker;
import com.goit.dto.YoungestEldestWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
        List<MaxProjectCountClient> maxProjectCountClientList = databaseQueryService.findMaxProjectsClient();
        List<MaxSalaryWorker> maxSalaryWorkerList = databaseQueryService.findMaxSalaryWorker();
        List<LongestProject> longestProjectList = databaseQueryService.findLongestProject();
        List<YoungestEldestWorker> youngestEldestWorkerList = databaseQueryService.findYoungestEldestWorker();
    }
}


