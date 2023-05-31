package org.wzhframework.workstation.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.wzhframework.workstation.func.QueryConsumer;

public final class PageHelpUtil {
    private PageHelpUtil() {
        super();
    }

    public static<T> Page<T> startPage(Integer pageNo, Integer pageSize, QueryConsumer consumer) {
        Page<T> page = PageHelper.startPage(pageNo, pageSize);
        consumer.apply();
        return page;
    }
}


