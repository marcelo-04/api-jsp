package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})//Intercepta todas as requisi��es que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
    }
    
    //Encerra os processo quando o servidor � parado
    //Mataria os processo de conex�o com banco
	public void destroy() {
	}

	//Intercepta as requisi��es e as respostas no sistema, tudo que fizer no sistema vai passar por aqui
	//Valida��o de autentica��o, dar commit e rolback de transa��es do banco
	//Validar e fazer redirecionamanto de p�ginas.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlParaAutenticar = req.getServletPath();// Url que est� sendo acessada
		
		//Validar se est� logado sen�o redireciona para a tela de login
		if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {// N�o est� logado
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+ urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login corretamente!!!");
			redireciona.forward(request, response);
			return;//Para a execu��o e direciona para o login
		} else {
			chain.doFilter(request, response);
		}
	}
	
	//Inicia os processo ou recursos quando o servidor sobe o projeto
	//Iniciar a conex�o com banco
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
