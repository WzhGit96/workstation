package org.wzhframework.workstation.context;

import org.wzhframework.workstation.common.GenericObject;

/**
 * @author wzh
 * @since 2023/2/22
 */
public final class RequestGenericContextHolder {
    private RequestGenericContextHolder() {
        super();
    }

    private static final ThreadLocal<GenericObject> contextHolder = new ThreadLocal<>();

    public static GenericObject getContext()
    {
        return contextHolder.get();
    }

    public static void setContext(GenericObject context)
    {
        contextHolder.set(context);
    }

    public static void clearContext()
    {
        contextHolder.remove();
    }
}
