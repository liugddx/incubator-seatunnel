package org.apache.seatunnel.api.table.catalog.exception;

import org.apache.seatunnel.api.common.SeaTunnelAPIErrorCode;
import org.apache.seatunnel.common.exception.SeaTunnelRuntimeException;

public class MetaLakeException extends SeaTunnelRuntimeException {

    /** @param message the detail message. */
    public MetaLakeException(String message) {
        super(SeaTunnelAPIErrorCode.META_LAKE_CONNECT_FAILED, message);
    }

    /** @param cause the cause. */
    public MetaLakeException(Throwable cause) {
        super(SeaTunnelAPIErrorCode.META_LAKE_CONNECT_FAILED, cause);
    }

    /**
     * @param message the detail message.
     * @param cause the cause.
     */
    public MetaLakeException(String message, Throwable cause) {
        super(SeaTunnelAPIErrorCode.META_LAKE_CONNECT_FAILED, message, cause);
    }
}
