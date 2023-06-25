package com.goit;

import com.goit.conf.FlywayConfigurations;
import com.goit.crud.Client;
import com.goit.crud.ClientService;
import com.goit.crud.JDBCRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        new FlywayConfigurations()
                .setup()
                .migrate();
        JDBCRepository<Client> repository = new ClientService(Database.getInstance().getConnection());
        List<Client> list = repository.listAll();
        System.out.println(list);
        repository.create("TEST");
        list = repository.listAll();
        System.out.println(list);
    }
}


