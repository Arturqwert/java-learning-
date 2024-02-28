package db.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.model.api.Company;
import db.model.api.CreateCompanyResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class CompanyServiceImpl implements CompanyService{

    HttpClient client;
    String URL;
    ObjectMapper mapper;

    public CompanyServiceImpl(HttpClient client, String url) {
        this.client = client;
        URL = url;
        mapper = new ObjectMapper();
    }

    @Override
    public List<Company> getAll() throws IOException {
        HttpGet get = new HttpGet(URL);
        return mapper.readValue(EntityUtils.toString(client.execute(get).getEntity()), new TypeReference<List<Company>>() {});
    }

    @Override
    public List<Company> getAll(boolean isActive) throws IOException {
        return null;
    }

    @Override
    public Company getById(int id) throws IOException {
        return null;
    }

    @Override
    public CreateCompanyResponse create(String name) throws IOException {
        return null;
    }

    @Override
    public CreateCompanyResponse create(String name, String description) throws IOException {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Company edit(int id, String newName) {
        return null;
    }

    @Override
    public Company edit(int id, String newName, String newDescription) {
        return null;
    }

    @Override
    public Company changeStatus(int id, boolean isActive) {
        return null;
    }
}
