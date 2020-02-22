package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.DaoException;
import dao.UtilisateurDao;
import environnement.Constantes;
import metier.forms.AjoutUtilisateurForm;
import metier.forms.ModifyUtilisateurForm;

/**
 * Servlet implementation class AjoutUtilisateur
 */
@WebServlet("/users/*")
public class GestionUtilisateur extends HttpServlet
{
	private static final long				serialVersionUID		= 1L;
	private static final String				VUE_AJOUT_UTILISATEUR	= "/WEB-INF/ajoutUtilisateur.jsp";
	private static final String				VUE_LIST_UTILISATEUR	= "/WEB-INF/listeUtilisateur.jsp";

	public static final List<Utilisateur>	utilisateurs			= new ArrayList<Utilisateur>();
	private static final String				VUE_UPDATE_UTILISATEUR	= "/WEB-INF/modifierUtilisateur.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/users/add"))
		{
			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith("/users/list"))
		{
			try {
				request.setAttribute("utilisateurs", UtilisateurDao.getList());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith(Constantes.LIEN_PAGE_USERS_MODIFY))
		{
			String login = request.getParameter("login");
			Utilisateur utilisateur = UtilisateurDao.select(login);
			
			if(utilisateur != null)
			{
				request.setAttribute("utilisateur", utilisateur);
				getServletContext().getRequestDispatcher(Constantes.VUE_PAGE_USERS_MODIFY)
				.forward(request, response);
			}else
			{
				response.sendRedirect(request.getContextPath() + Constantes.VUE_PAGE_USERS_LIST + "?message=Un utilisateur avec un tel login n'existe pas!");
			}

			
		}
		else if (requestedUrl.endsWith(Constantes.LIEN_PAGE_USERS_DELETE))
		{
			String login = request.getParameter("login");
			if(login != null && login.trim() != "")
			{
				if(UtilisateurDao.delete(login) > 0)
				{
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_USERS_LIST + "?message=L\'utilisateur "+login+" a ete supprime avec success!&statut=succes");
				}else
				{
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_USERS_LIST + "?message=Il n\'existe aucun utilisateur avec le login "+login+"!&statut=erreur");
				}
				
			}else
			{
				response.sendRedirect(Constantes.VUE_PAGE_ERROR + "?errorMessage=Ce login d\'utilisateur n\'existe pas!");
			}
		}
		else if (requestedUrl.endsWith(Constantes.LIEN_PAGE_USERS_VIEW))
		{
			String login = request.getParameter("login");

			if(login != null && login.trim() != "") {
			
				request.setAttribute("utilisateur", UtilisateurDao.select(login));	
				
				getServletContext().getRequestDispatcher(Constantes.VUE_PAGE_USERS_VIEW).forward(request, response);
		  }else{
			  
			  response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage='Vous n\'avez renseigner aucun login!'");
		  }
		  				
		}
		else if (requestedUrl.endsWith(Constantes.LIEN_PAGE_USERS_PROFIL))
		{
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				
				if(utilisateur != null) {
					request.setAttribute("utilisateur", utilisateur);				
					getServletContext().getRequestDispatcher(Constantes.VUE_PAGE_USERS_PROFIL).forward(request, response);
				}else {
				response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage='Oups une petite erreur, veuillez contacter l'administrateur du site!'");
				}
		}
		else
		{
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/users/add"))
		{
			AjoutUtilisateurForm form = new AjoutUtilisateurForm(request);
			Utilisateur utilisateur = form.getUtilisateur();

			if (form.isValid())
			{
				try {
					UtilisateurDao.ajouter(utilisateur);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("messageErreurs", form.getMessageErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());

			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
					.forward(request, response);
		}else if (requestedUrl.endsWith("/users/modify"))
		{
			ModifyUtilisateurForm form = new ModifyUtilisateurForm(request);
			Utilisateur utilisateur = form.getUtilisateur();

			if (form.isValid())
			{
				int res = UtilisateurDao.modify(utilisateur, form.loginChanged() ? form.getLastLogin() : utilisateur.getLogin());
				if(res > 0)
				{
					request.setAttribute("statusMessage", "Modification de l'utilisateur "+form.getLastLogin()+ " reussie!");
					request.removeAttribute("lastLogin");
				}else if(res == 0)
				{
					request.setAttribute("statusMessage", "L'utilisateur "+form.getLastLogin()+ " que vous voulez modifier n'existe pas!");
					request.setAttribute("lastLogin", form.getLastLogin());
					form.getMessageErreurs().put("login_not_exist", "utilisateur modifié n'existe pas!");
				}else
				{
					request.setAttribute("statusMessage", "L'utilisateur "+utilisateur.getLogin()+ " existe déjà!");
					form.getMessageErreurs().put("bd_constraint", "utilisateur renseigné existe déjà!");
					request.setAttribute("lastLogin", form.getLastLogin());
				}
					
			}else
			{
				request.setAttribute("statusMessage", "Formulaire Incorrect!");
				request.setAttribute("lastLogin", form.getLastLogin());
			}
			

			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("messageErreurs", form.getMessageErreurs());
			;

			getServletContext().getRequestDispatcher(Constantes.VUE_PAGE_USERS_MODIFY)
					.forward(request, response);
		}
	}

	public static List<Utilisateur> getUtilisateurs()
	{
		return utilisateurs;
	}
}
