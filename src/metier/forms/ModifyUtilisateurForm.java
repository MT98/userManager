package metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public class ModifyUtilisateurForm
{
	private static final String	CHAMP_LAST_LOGIN	= "lastLogin";
	private static final String	CHAMP_NOM			= "nom";
	private static final String	CHAMP_PRENOM		= "prenom";
	private static final String	CHAMP_LOGIN			= "login";
	private static final String	CHAMP_PASSWORD		= "password";
	private static final String	CHAMP_PASSWORD_BIS	= "passwordBis";

	private String lastLogin;
	private HttpServletRequest	request;
	private Utilisateur			utilisateur;
	private Map<String, String>	messageErreurs		= new HashMap<String, String>();

	public ModifyUtilisateurForm(HttpServletRequest request)
	{
		this.request = request;

		String nom = getParamater(CHAMP_NOM);
		String prenom = getParamater(CHAMP_PRENOM);
		String login = getParamater(CHAMP_LOGIN);
		String password = getParamater(CHAMP_PASSWORD);
		this.lastLogin = getParamater(CHAMP_LAST_LOGIN);

		utilisateur = new Utilisateur(nom, prenom, login, password);

		validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD,
				CHAMP_PASSWORD_BIS);
		validerPasswords();
	}

	private void validerChamps(String... champs)
	{
		for (String champ : champs)
		{
			if (getParamater(champ) == null)
			{
				messageErreurs.put(champ, "Vous devez renseigner ce champ");
			}
		}
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		return valeur;
	}

	private void validerPasswords()
	{
		String password = getParamater(CHAMP_PASSWORD);
		String passwordBis = getParamater(CHAMP_PASSWORD_BIS);
		if (password != null && !password.equals(passwordBis))
		{
			messageErreurs.put(CHAMP_PASSWORD,
					"Les mots de passe ne sont pas conformes");
			messageErreurs.put(CHAMP_PASSWORD_BIS,
					"Les mots de passe ne sont pas conformes");
		}
	}

	public Utilisateur getUtilisateur()
	{
		return utilisateur;
	}


	public Map<String, String> getMessageErreurs()
	{
		return messageErreurs;
	}

	public boolean isValid()
	{
		return messageErreurs.isEmpty();
	}

	public boolean loginChanged()
	{
		return !this.lastLogin.equals(utilisateur.getLogin());
	}
}
