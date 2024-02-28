package db;

import db.ext.MyPersistenceUnitInfo;
import db.model.db.HCompanyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Properties;

public class sandbox {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        props.put("hibernate.connection.username", "postgres");
        props.put("hibernate.connection.password", "password");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.connection.autocommit", "true");
        props.put("hibernate.hbm2ddl.auto", "validate");


        PersistenceUnitInfo persistenceUnitInfo = new MyPersistenceUnitInfo(props);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, props);
        EntityManager entityManager = factory.createEntityManager();

        HCompanyEntity companyEntity = entityManager.find(HCompanyEntity.class, 1);
        System.out.println(companyEntity);

    }
}
