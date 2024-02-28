package db.db;

import db.model.db.CompanyEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryJDBC implements CompanyRepository {
    private Connection connection;
    private String getAllQuery = "select * from \"x-clients\".company";

    public CompanyRepositoryJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CompanyEntity> getAll() throws SQLException {
        ResultSet res = connection.createStatement().executeQuery(getAllQuery);

        List<CompanyEntity> companies = new ArrayList<>();

        while (res.next()) {
            companies.add(new CompanyEntity(
                    res.getInt("id"),
                    res.getBoolean("isActive"),
                    res.getString("name"),
                    res.getString("description"),
                    res.getBoolean("deletedAt"),
                    res.getTimestamp("createDateTime"),
                    res.getTimestamp("lastChangedDateTime")
            ));
        }

        return companies;
    }

    @Override
    public List<CompanyEntity> getAll(boolean isActive) {
        return null;
    }

    @Override
    public CompanyEntity getLast() throws SQLException {
        return null;
    }

    @Override
    public CompanyEntity getById(int id) {
        return null;
    }

    @Override
    public CompanyEntity create(String name) throws SQLException {
        return null;
    }

    @Override
    public CompanyEntity create(String name, String description) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
