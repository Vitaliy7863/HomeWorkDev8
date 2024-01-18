package org.example.service;

import org.example.db.Database;
import org.example.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final String CREATE_CLIENT = "INSERT INTO client (name) VALUES (?)";
    private static final String GET_NAME = "SELECT name FROM client WHERE id = ?";
    private static final String UPDATE_NAME = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String GET_ALL_CLIENT = "SELECT * FROM client";

    public long create(String name) {
        long id = 0L;
        if (name.length() <= 1000 && name.length() > 2) {
            try (Connection connection = Database.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }

                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Incorrect name length");
        }
            return id;
    }

    public String getById(long id) {
        String name = "";
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_NAME);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString("name");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    public void setName(long id, String name) {
        if (name.length() <= 1000 && name.length() > 2) {
            try (Connection connection = Database.getConnection();) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME);
                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();

                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Incorrect name length");
        }
    }

    public void deleteById(long id) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENT);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                clients.add(client);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }
}
