package com.lingh.pojo;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Grantee.
 */
@RequiredArgsConstructor
public final class Grantee {
    
    @Getter
    private final String username;
    
    private final String hostname;
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Grantee) {
            Grantee grantee = (Grantee) obj;
            return grantee.username.equalsIgnoreCase(username) && isPermittedHost(grantee);
        }
        return false;
    }
    
    private boolean isPermittedHost(final Grantee grantee) {
        return grantee.hostname.equalsIgnoreCase(hostname) || isUnlimitedHost();
    }
    
    private boolean isUnlimitedHost() {
        return Strings.isNullOrEmpty(hostname) || "%".equals(hostname);
    }
    
    /**
     * Get host name.
     * 
     * @return host name
     */
    public String getHostname() {
        return Strings.isNullOrEmpty(hostname) ? "%" : hostname;
    }
    
    @Override
    public int hashCode() {
        return isUnlimitedHost() ? Objects.hashCode(username.toUpperCase()) : Objects.hashCode(username.toUpperCase(), hostname.toUpperCase());
    }
    
    @Override
    public String toString() {
        return String.format("%s@%s", username, hostname);
    }
}
