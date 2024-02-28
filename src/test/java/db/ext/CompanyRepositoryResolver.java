package db.ext;

import db.db.CompanyRepository;
import db.db.CompanyRepositoryJDBC;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CompanyRepositoryResolver implements ParameterResolver {
    String connectionString = System.getProperty("jdbcConnection");
    String login = System.getProperty("jdbcLog");
    String password = System.getProperty("jdbcPass");
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(connectionString, login, password);
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return new CompanyRepositoryJDBC(connection);
    }
}
