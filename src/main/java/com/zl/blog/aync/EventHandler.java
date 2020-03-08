package com.zl.blog.aync;

import java.util.List;

/**
 * Created by zl on 17-7-26.
 * @author zl
 */
public interface EventHandler {
    void doHandler(EventModel model);

    List<EventType> getSupportEventTypes();
}
