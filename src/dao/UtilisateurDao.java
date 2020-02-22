package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Utilisateur;

public class UtilisateurDao
{
	private static final String	AJOUT_UTILISATEUR_SQL	= "INSERT INTO utilisateur(nom, prenom, login, password) VALUES(?, ?, ?, ?)";
	private static final String	SELECT_TOUT_UTILISATEUR_SQL	= "SELECT * FROM utilisateur";
	private static final String	SELECT_UTILISATEUR_SQL	= "SELECT * FROM utilisateur WHERE login LIKE ?";
	private static final String DELETE_UTILISATEUR_SQL = "DELETE FROM utilisateur WHERE login LIKE ?";
	private static final String UPDATE_UTILISATEUR_SQL = "UPDATE utilisateur SET nom = ?, prenom = ?, login = ?, password = ? WHERE login LIKE ?";

	public static void ajouter(Utilisateur utilisateur) throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		try
		{
			PreparedStatement statement = connexion
					.prepareStatement(AJOUT_UTILISATEUR_SQL);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getLogin());
			statement.setString(4, utilisateur.getPassword());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DaoException("Utilisateur non ajouté");
		}
	}

	public static List<Utilisateur> getList() throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Utilisateur> utilisateurs = null;
		try
		{
			utilisateurs = new ArrayList<Utilisateur>();
			String nom, prenom, login, password;
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SELECT_TOUT_UTILISATEUR_SQL);
			while (resultSet.next())
			{
				nom = resultSet.getString(2);
				prenom = resultSet.getString(3);
				login = resultSet.getString(4);
				password = resultSet.getString(5);
				utilisateurs.add(new Utilisateur(nom, prenom, login, password));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateurs;
	}

	public static Utilisateur select(String login) {
		// TODO Auto-generated method stub
		
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement stm = connection.prepareStatement(SELECT_UTILISATEUR_SQL);
			
			stm.setString(1, login);
		
			ResultSet rs = stm.executeQuery();
			if(rs.first())
			{
				return new Utilisateur(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 	
		
	}
	
	public static int delete(String login)
	{
		Connection connection;
		try {
			connection = DatabaseManager.getConnection();
			PreparedStatement smt = connection.prepareStatement(DELETE_UTILISATEUR_SQL);
			smt.setString(1, login);
			
			return smt.executeUpdate();
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public static int modify(Utilisateur utilisateur, String login)
	{
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement stm = connection.prepareStatement(UPDATE_UTILISATEUR_SQL);
			stm.setString(1, utilisateur.getNom());
			stm.setString(2, utilisateur.getPrenom());
			stm.setString(3, utilisateur.getLogin());
			stm.setString(4, utilisateur.getPassword());
			stm.setString(5, login);
			
			return stm.executeUpdate();
		
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
}
