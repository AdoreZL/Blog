package com.example.threadsafe;

import com.zl.blog.WebApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/6 22:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
public class UnsafeCountingServlet extends GenericServlet implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ++count;
    }
}
