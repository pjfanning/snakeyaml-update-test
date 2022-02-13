package org.example.jackson.ssInterface;

/**
 * Ordered SPI.
 * 
 * @param <T> type
 */
public interface OrderedSPI<T> {
    
    /**
     * Get order of load.
     *
     * @return load order
     */
    int getOrder();
    
    /**
     * Get type class.
     * 
     * @return type class
     */
    Class<T> getTypeClass();
}
