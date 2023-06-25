package com.goit.crud;

import com.goit.exeption.DatasourceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends JDBCRepository<Client> {
    private PreparedStatement createSt;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement listAll;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO CLIENT (NAME) VALUES (?);"
        );
        readSt = connection.prepareStatement(
                "SELECT * FROM CLIENT WHERE ? = ?"
        );
        updateSt = connection.prepareStatement(
            "UPDATE CLIENT SET NAME = ? WHERE ID = ?"
        );
        deleteSt = connection.prepareStatement(
                "DELETE FROM CLIENT WHERE ID = ?"
        );
        listAll = connection.prepareStatement(
          "SELECT * FROM CLIENT"
        );
    }
    @Override
    public Long create(String name){
        try {
            createSt.setString(1, name);
            createSt.executeUpdate();
            return getByName(name);
        } catch (Exception e){
            String message = "create";
            throw new DatasourceException(message, e);
        }
    }

    @Override
    public List<Client> listAll() {
        try {
            ResultSet rs = listAll.executeQuery();
            List<Client> list = new ArrayList<>();
            while (rs.next()){
                Client client = parseCustomerRow(rs);
                list.add(client);
            }
            return list;
        }catch (Exception e){
            String message = "listAll";
            throw new DatasourceException(message, e);
        }
    }

    private Client parseCustomerRow(ResultSet resultSet) throws SQLException {
        Long clientId = resultSet.getLong("id");
        String clientName = resultSet.getString("name");
        return new Client(clientId, clientName);
    }

    @Override
    public void setName(Long id, String name){
        try{
            updateSt.setString(1, name);
            updateSt.setLong(2, id);
            updateSt.executeUpdate();
        }catch (Exception e){
            String message = "setName";
            throw new DatasourceException(message, e);
        }
    }

    @Override
    public void deleteById(Long id){
        try {
            deleteSt.setLong(1, id);
            deleteSt.executeUpdate();
        } catch (Exception e) {
            String message = "deleteById";
            throw new DatasourceException(message, e);
        }
    }

    @Override
    public String getById(Long id) {
        try {
            readSt.setString(1, "id");
            readSt.setLong(2, id);
            ResultSet rs = readSt.executeQuery();
            if(!rs.next()){
                return null;
            }
            return rs.getString("name");
        } catch (Exception e) {
            String message = "getById";
            throw new DatasourceException(message, e);
        }
    }
    public Long getByName(String name) {
        try {
            readSt.setString(1, "name");
            readSt.setString(2, name);
            ResultSet rs = readSt.executeQuery();
            if(!rs.next()){
                return null;
            }
            return rs.getLong("id");
        } catch (Exception e) {
            String message = "getByName";
            throw new DatasourceException(message, e);
        }
    }
}
