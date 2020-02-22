package metier.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import servlets.GestionUtilisateur;
import dao.DaoException;
import dao.UtilisateurDao;
import environnement.Constantes;

public class AuthenticationForm
{
	private static final String	CHAMP_LOGIN		= "login";
	private static final String	CHAMP_PASSWORD	= "password";
	private HttpServletRequest	request;
	private String				login;

	public AuthenticationForm(HttpServletRequest request)
	{
		this.request = request;
	}

	public Utilisateur connect()
	{
		login = getParamater(CHAMP_LOGIN);
		String password = getParamater(CHAMP_PASSWORD);

		List<Utilisateur> utilisateurs = null;
		try {
			utilisateurs = UtilisateurDao.getList();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getLogin().equals(login)
					&& utilisateur.getPassword().equals(password))
			{
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				return utilisateur;
			}
		}
		if (Constantes.ADMIN_LOGIN.equals(login) && Constantes.ADMIN_PASSWORD.equals(password))
		{
			HttpSession session = request.getSession();
			Utilisateur utilisateur = new Utilisateur("ADMIN", "ADMIN", "admin",
					"admin");
			session.setAttribute("utilisateur", utilisateur);
			return utilisateur;
		}
		else
		{
			return null;
		}
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		return valeur;
	}

	public String getLogin()
	{
		return login;
	}
}
