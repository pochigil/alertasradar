package gcm;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Po
 */
@WebServlet("/Login")
public class Login extends HttpServlet{

    private final List<String> usuarios;
    
    public Login() throws IOException, ServletException {
        super();
        
        JsonParser json = new JsonParser();
        usuarios = json.ReadUsers();
        
    }
    
    /**
     *
     * @param request
     * @param response
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException{
        
        String usuario = "";
        String password = "";
        
        try {
            usuario = request.getParameter("usuario");
            password = request.getParameter("password");
            
            boolean login = false;
            
            for (int i = 0; i < usuarios.size(); i++) {
                String[] split = usuarios.get(i).split(",");
                if(split[0].equals(usuario)){
                    if(split[1].equals(password)){
                        login = true;
                        break;
                    }
                }
            }
            
            if(login){
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("alerta.jsp");
            }else{
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
               
    }
    
}
