package db;

import db.api.CompanyService;
import db.db.CompanyRepository;
import db.db.CompanyRepositoryJPA;
import db.ext.CompanyRepositoryJpaResolver;
import db.ext.CompanyRepositoryResolver;
import db.ext.CompanyServiceResolver;
import db.model.api.Company;
import db.model.db.CompanyEntity;
import db.model.db.HCompanyEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith({CompanyRepositoryJpaResolver.class, CompanyServiceResolver.class})
@ExtendWith({CompanyRepositoryResolver.class, CompanyServiceResolver.class})
public class CompanyTest {

    @Test
    @Tag("runMe")
    @DisplayName("тест сверяет количество компаний в ответе от сервера и бд.")
    public void shouldReceiveListCompanies(CompanyRepository repository, CompanyService service) throws IOException, SQLException {
        List<Company> companiesFromService = service.getAll();
        var companiesFromDb = repository.getAll();
        assertEquals(companiesFromService.size(), companiesFromDb.size());
    }
}
