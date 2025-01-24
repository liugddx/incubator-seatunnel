package org.apache.seatunnel.api.table.metalake;

import org.apache.seatunnel.api.configuration.ReadonlyConfig;
import org.apache.seatunnel.api.table.catalog.exception.MetaLakeException;

import java.util.Properties;

public interface MetaLake {

    void open() throws MetaLakeException;

    void close() throws MetaLakeException;

    /**
     * Getting basic information about a data connection from a remote catalog.
     *
     * @return the information of the current catalog
     * @throws MetaLakeException in case of any runtime exception
     */
    Properties getCatalogInfo() throws MetaLakeException;

    /**
     * Get the parameter mapping of the connector type
     *
     * @param connectorType connector type
     * @return the parameter mapping of the connector type
     * @throws MetaLakeException in case of any runtime exception
     */
    ReadonlyConfig parameterMappingByConnectorType(
            String connectorType, ReadonlyConfig readonlyConfig) throws MetaLakeException;
}
