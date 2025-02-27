package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "TaJiFilter", value = "/TaJiMain/*")
public class TaJiFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String userName = (String) session.getAttribute("name");
        if (userName != null) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect("../account.html");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
