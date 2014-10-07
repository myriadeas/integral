/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package my.com.myriadeas.integral.security.jpa;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.MethodSecurityMetadataSource;


/**
 * Abstract implementation of <tt>MethodSecurityMetadataSource</tt> which resolves the secured object type to
 * a MethodInvocation.
 *
 * @author Ben Alex
 * @author Luke Taylor
 */
public abstract class JpaRepositoryAbstractMethodSecurityMetadataSource implements MethodSecurityMetadataSource {

    protected final Logger logger = LoggerFactory.getLogger(JpaRepositoryAbstractMethodSecurityMetadataSource.class);

    //~ Methods ========================================================================================================

    public final Collection<ConfigAttribute> getAttributes(Object object) {
        if (object instanceof MethodInvocation) {
            MethodInvocation mi = (MethodInvocation) object;
            Object target = mi.getThis();
            Class<?> targetClass = null;
            if (target != null) {
                targetClass = target instanceof Class<?> ? (Class<?>)target : AopProxyUtils.ultimateTargetClass(target);
            }

            //TODO - not sure it is correct way of doing. Spring Jpa Data instantiate using auto proxy. Nested proxy.
            //Hence, if it is SimpleJpaRepository assignable, don't get the ultimate class but the proxy class instead.
            //If get ultimate class, then it is always SimpleJpaRepository and no permission applied. permission applied on
            // ext interface and based on proxy itself.
            if(targetClass.isAssignableFrom(SimpleJpaRepository.class)) {
            	return getAttributes(mi.getMethod(), target.getClass());
            }
            
            return getAttributes(mi.getMethod(), targetClass);
        }

        throw new IllegalArgumentException("Object must be a non-null MethodInvocation");
    }

    public final boolean supports(Class<?> clazz) {
        return (MethodInvocation.class.isAssignableFrom(clazz));
    }
}
