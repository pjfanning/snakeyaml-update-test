package com.lingh.ssException;

/**
 * Service loader instantiation exception.
 */
public final class ServiceLoaderInstantiationException extends RuntimeException {
    
    private static final long serialVersionUID = 6261274443437676201L;
    
    public ServiceLoaderInstantiationException(final Class<?> clazz, final Exception cause) {
        super(String.format("Can not find public default constructor for SPI class `%s`", clazz.getName()), cause);
    }
}