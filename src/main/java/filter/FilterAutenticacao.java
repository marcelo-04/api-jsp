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


@WebFilter(urlPatterns = {"/principal/*"})//Intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
    }
    
    //Encerra os processo quando o servidor é parado
    //Mataria os processo de conexão com banco
	public void destroy() {
	}

	//Intercepta as requisições e as respostas no sistema, tudo que fizer no sistema vai passar por aqui
	//Validação de autenticação, dar commit e rolback de transações do banco
	//Validar e fazer redirecionamanto de páginas.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlParaAutenticar = req.getServletPath();// Url que está sendo acessada
		
		//Validar se está logado senão redireciona para a tela de login
		if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {// Não está logado
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+ urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login corretamente!!!");
			redireciona.forward(request, response);
			return;//Para a execução e direciona para o login
		} else {
			chain.doFilter(request, response);
		}
	}
	
	//Inicia os processo ou recursos quando o servidor sobe o projeto
	//Iniciar a conexão com banco
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
