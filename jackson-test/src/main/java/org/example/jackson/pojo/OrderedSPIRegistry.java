package org.example.jackson.pojo;

import com.google.common.base.Preconditions;
import org.example.jackson.ssInterface.OrderedSPI;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Ordered SPI registry.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderedSPIRegistry {

    /**
     * Get registered services by class type.
     *
     * @param orderedSPIClass class of ordered SPI
     * @param types           types
     * @param <T>             type of ordered SPI class
     * @return registered services
     */
    public static <T extends OrderedSPI<?>> Map<Class<?>, T> getRegisteredServicesByClass(final Class<T> orderedSPIClass, final Collection<Class<?>> types) {
        Collection<T> registeredServices = getRegisteredServices(orderedSPIClass);
        Map<Class<?>, T> result = new LinkedHashMap<>(registeredServices.size(), 1);
        for (T each : registeredServices) {
            types.stream().filter(type -> each.getTypeClass() == type).forEach(type -> result.put(type, each));
        }
        return result;
    }

    /**
     * Get registered services.
     *
     * @param orderedSPIClass class of ordered SPI
     * @param types           types
     * @param <K>             type of key
     * @param <V>             type of ordered SPI class
     * @return registered services
     */
    public static <K, V extends OrderedSPI<?>> Map<K, V> getRegisteredServices(final Class<V> orderedSPIClass, final Collection<K> types) {
        return getRegisteredServices(orderedSPIClass, types, Comparator.naturalOrder());
    }

    /**
     * Get registered services.
     *
     * @param orderedSPIClass class of ordered SPI
     * @param types           types
     * @param <K>             type of key
     * @param <V>             type of ordered SPI class
     * @param comparator      comparator
     * @return registered services
     */
    @SuppressWarnings("unchecked")
    public static <K, V extends OrderedSPI<?>> Map<K, V> getRegisteredServices(final Class<V> orderedSPIClass, final Collection<K> types, final Comparator<Integer> comparator) {
        Optional<Map<K, V>> cachedServices = OrderedServicesCache.findCachedServices(orderedSPIClass, types).map(optional -> (Map<K, V>) optional);
        if (cachedServices.isPresent()) {
            return cachedServices.get();
        }
        Collection<V> registeredServices = getRegisteredServices(orderedSPIClass, comparator);
        Map<K, V> result = new LinkedHashMap<>(registeredServices.size(), 1);
        for (V each : registeredServices) {
            types.stream().filter(type -> each.getTypeClass() == type.getClass()).forEach(type -> result.put(type, each));
        }
        OrderedServicesCache.cacheServices(orderedSPIClass, types, result);
        return result;
    }

    /**
     * Get registered services.
     *
     * @param orderedSPIClass class of ordered SPI
     * @param <T>             type of ordered SPI class
     * @return registered services
     */
    public static <T extends OrderedSPI<?>> Collection<T> getRegisteredServices(final Class<T> orderedSPIClass) {
        return getRegisteredServices(orderedSPIClass, Comparator.naturalOrder());
    }

    /**
     * Get registered services.
     *
     * @param orderedSPIClass class of ordered SPI
     * @param <T>             type of ordered SPI class
     * @param comparator      comparator
     * @return registered services
     */
    public static <T extends OrderedSPI<?>> Collection<T> getRegisteredServices(final Class<T> orderedSPIClass, final Comparator<Integer> comparator) {
        Map<Integer, T> result = new TreeMap<>(comparator);
        for (T each : ShardingSphereServiceLoader.getSingletonServiceInstances(orderedSPIClass)) {
            Preconditions.checkArgument(!result.containsKey(each.getOrder()), "Found same order `%s` with `%s` and `%s`", each.getOrder(), result.get(each.getOrder()), each);
            result.put(each.getOrder(), each);
        }
        return result.values();
    }
}
