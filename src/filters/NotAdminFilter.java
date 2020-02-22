package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import environnement.Constantes;

/**
 * Servlet Filter implementation class NotAdminFilter
 */
@WebFilter(description = "Filtre de contrôle d'acces aux fonctionnalités d'administration.", urlPatterns = { "/users/list", "/users/add", "/users/delete", "/users/view", "/users/modify" })
public class NotAdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NotAdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
	
		if(session != null &&  session.getAttribute("utilisateur") != null && ((Utilisateur)session.getAttribute("utilisateur")).getLogin() != Constantes.ADMIN_LOGIN)
		{
			((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage='Vous ne disposez pas assez de droit pour acceder a cette page!'");
		}else
		{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
