package db.api;

import db.model.api.Company;
import db.model.api.CreateCompanyResponse;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    List<Company> getAll() throws IOException;

    List<Company> getAll(boolean isActive) throws IOException;

    Company getById(int id) throws IOException;

    CreateCompanyResponse create(String name) throws IOException;

    CreateCompanyResponse create(String name, String description) throws IOException;

    void deleteById(int id);

    Company edit(int id, String newName);

    Company edit(int id, String newName, String newDescription);

    Company changeStatus(int id, boolean isActive);
}
