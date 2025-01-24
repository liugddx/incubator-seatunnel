package org.apache.seatunnel.api.table.factory;

import org.apache.seatunnel.api.configuration.ReadonlyConfig;
import org.apache.seatunnel.api.table.metalake.MetaLake;

public interface MetaLakeFactory extends Factory {
    /** Creates a {@link MetaLake} using the options. */
    MetaLake createCatalog(String metaLakeName, ReadonlyConfig options);
}
