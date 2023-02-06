package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		String sql = "INSERT INTO public.model_login(login, senha, nome, email) "
				                         + "VALUES (?, ?, ?, ?);";
		PreparedStatement prepSql = connection.prepareStatement(sql);
		prepSql.setString(1, objeto.getLogin());
		prepSql.setString(2, objeto.getSenha());
		prepSql.setString(3, objeto.getNome());
		prepSql.setString(4, objeto.getEmail());
		
		prepSql.execute();
		connection.commit();
		
		return this.consultaUsuario(objeto.getLogin());
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {// Se tiver resultado entra aqui e vai preencher
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
		}
		return modelLogin;
	}
}
