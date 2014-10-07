package my.com.myriadeas.integral.security.jpa;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Automatically tries a series of method definition sources, relying on the
 * first source of metadata that provides a non-null/non-empty response.
 * Provides automatic caching of the retrieved metadata. Copied from original
 * Spring Security's
 * {@link org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource}
 * 
 * @author Ben Alex
 * @author Luke Taylor
 */
public final class JpaRepositoryDelegatingMethodSecurityMetadataSource extends
		JpaRepositoryAbstractMethodSecurityMetadataSource  {
	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections
			.emptyList();

	private final List<MethodSecurityMetadataSource> methodSecurityMetadataSources;
	private final Map<DefaultCacheKey, Collection<ConfigAttribute>> attributeCache = new HashMap<DefaultCacheKey, Collection<ConfigAttribute>>();

	// ~ Constructor
	// ====================================================================================================

	public JpaRepositoryDelegatingMethodSecurityMetadataSource(
			List<MethodSecurityMetadataSource> methodSecurityMetadataSources) {
		Assert.notNull(methodSecurityMetadataSources,
				"MethodSecurityMetadataSources cannot be null");
		this.methodSecurityMetadataSources = methodSecurityMetadataSources;
	}

	// ~ Methods
	// ========================================================================================================

	public Collection<ConfigAttribute> getAttributes(Method method,
			Class<?> targetClass) {
		DefaultCacheKey cacheKey = new DefaultCacheKey(method, targetClass);
		synchronized (attributeCache) {
			Collection<ConfigAttribute> cached = attributeCache.get(cacheKey);
			// Check for canonical value indicating there is no config
			// attribute,
			if (cached != null) {
				return cached;
			}

			// No cached value, so query the sources to find a result
			Collection<ConfigAttribute> attributes = null;
			for (MethodSecurityMetadataSource s : methodSecurityMetadataSources) {
				attributes = s.getAttributes(method, targetClass);
				if (attributes != null && !attributes.isEmpty()) {
					break;
				}
			}

			// Put it in the cache.
			if (attributes == null || attributes.isEmpty()) {
				this.attributeCache.put(cacheKey, NULL_CONFIG_ATTRIBUTE);
				return NULL_CONFIG_ATTRIBUTE;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Caching method [" + cacheKey
						+ "] with attributes " + attributes);
			}

			this.attributeCache.put(cacheKey, attributes);

			return attributes;
		}
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> set = new HashSet<ConfigAttribute>();
		for (MethodSecurityMetadataSource s : methodSecurityMetadataSources) {
			Collection<ConfigAttribute> attrs = s.getAllConfigAttributes();
			if (attrs != null) {
				set.addAll(attrs);
			}
		}
		return set;
	}

	public List<MethodSecurityMetadataSource> getMethodSecurityMetadataSources() {
		return methodSecurityMetadataSources;
	}

	// ~ Inner Classes
	// ==================================================================================================

	private static class DefaultCacheKey {
		private final Method method;
		private final Class<?> targetClass;

		public DefaultCacheKey(Method method, Class<?> targetClass) {
			this.method = method;
			this.targetClass = targetClass;
		}

		public boolean equals(Object other) {
			DefaultCacheKey otherKey = (DefaultCacheKey) other;
			return (this.method.equals(otherKey.method) && ObjectUtils
					.nullSafeEquals(this.targetClass, otherKey.targetClass));
		}

		public int hashCode() {
			return this.method.hashCode()
					* 21
					+ (this.targetClass != null ? this.targetClass.hashCode()
							: 0);
		}

		public String toString() {
			return "CacheKey["
					+ (targetClass == null ? "-" : targetClass.getName())
					+ "; " + method + "]";
		}
	}
}
