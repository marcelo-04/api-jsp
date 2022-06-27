package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ModelLogin;

// O chamado Controller são as servlets ou ServletLoginController
@WebServlet("/ServletLogin") // Mapeamento de URL que vem da tela
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        super();
    }
    
    // Recebe os dados pela URL em parâmetros
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
	
	// Recebe os dados enviados por um formulário
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (modelLogin.getLogin().equalsIgnoreCase("admin") 
					&& modelLogin.getSenha().equalsIgnoreCase("admin")){ // simulando login
				
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
				redirecionar.forward(request, response);
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Por gentileza digite o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		
		} else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Por gentileza digite o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
	}
}
