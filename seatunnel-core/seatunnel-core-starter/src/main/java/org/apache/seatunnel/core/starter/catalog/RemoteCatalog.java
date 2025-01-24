package org.apache.seatunnel.core.starter.catalog;

import org.apache.seatunnel.api.table.catalog.Catalog;
import org.apache.seatunnel.api.table.catalog.CatalogTable;
import org.apache.seatunnel.api.table.catalog.TablePath;
import org.apache.seatunnel.api.table.catalog.exception.CatalogException;
import org.apache.seatunnel.api.table.catalog.exception.DatabaseAlreadyExistException;
import org.apache.seatunnel.api.table.catalog.exception.DatabaseNotExistException;
import org.apache.seatunnel.api.table.catalog.exception.TableAlreadyExistException;
import org.apache.seatunnel.api.table.catalog.exception.TableNotExistException;

import java.util.List;
import java.util.Properties;

public abstract class RemoteCatalog implements Catalog {

    /**
     * Getting basic information about a data connection from a remote catalog.
     *
     * @return the information of the current catalog
     * @throws CatalogException in case of any runtime exception
     */
    public abstract Properties getCatalogInfo() throws CatalogException;

    public abstract Properties parameterMappingByConnectorType(String i) throws CatalogException;

    @Override
    public String getDefaultDatabase() throws CatalogException {
        throw new UnsupportedOperationException(
                "getDefaultDatabase is not supported in RemoteCatalog");
    }

    @Override
    public boolean databaseExists(String databaseName) throws CatalogException {
        throw new UnsupportedOperationException("databaseExists is not supported in RemoteCatalog");
    }

    @Override
    public List<String> listDatabases() throws CatalogException {
        throw new UnsupportedOperationException("listDatabases is not supported in RemoteCatalog");
    }

    @Override
    public List<String> listTables(String databaseName)
            throws CatalogException, DatabaseNotExistException {
        throw new UnsupportedOperationException("listTables is not supported in RemoteCatalog");
    }

    @Override
    public boolean tableExists(TablePath tablePath) throws CatalogException {
        throw new UnsupportedOperationException("tableExists is not supported in RemoteCatalog");
    }

    @Override
    public CatalogTable getTable(TablePath tablePath)
            throws CatalogException, TableNotExistException {
        throw new UnsupportedOperationException("getTable is not supported in RemoteCatalog");
    }

    @Override
    public void createTable(TablePath tablePath, CatalogTable table, boolean ignoreIfExists)
            throws TableAlreadyExistException, DatabaseNotExistException, CatalogException {
        throw new UnsupportedOperationException("createTable is not supported in RemoteCatalog");
    }

    @Override
    public void dropTable(TablePath tablePath, boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException {
        throw new UnsupportedOperationException("dropTable is not supported in RemoteCatalog");
    }

    @Override
    public void createDatabase(TablePath tablePath, boolean ignoreIfExists)
            throws DatabaseAlreadyExistException, CatalogException {
        throw new UnsupportedOperationException("createDatabase is not supported in RemoteCatalog");
    }

    @Override
    public void dropDatabase(TablePath tablePath, boolean ignoreIfNotExists)
            throws DatabaseNotExistException, CatalogException {
        throw new UnsupportedOperationException("dropDatabase is not supported in RemoteCatalog");
    }
}
