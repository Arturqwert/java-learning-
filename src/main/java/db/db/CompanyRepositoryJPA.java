package db.db;

import db.model.db.CompanyEntity;
import db.model.db.HCompanyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.SQLException;
import java.util.List;

public class CompanyRepositoryJPA{
    private final EntityManager manager;
    public CompanyRepositoryJPA(EntityManager manager) {
        this.manager = manager;
    }

    public List<HCompanyEntity> getAll() throws SQLException {
        TypedQuery<HCompanyEntity> selectAll = manager.createQuery("SELECT c FROM HCompanyEntity c", HCompanyEntity.class);
        var res = selectAll.getResultList();
        System.out.println("\nOutput from DB======>\n");
        for (HCompanyEntity company : res) {
            System.out.println(company);
        }
        return res;
    }


}
