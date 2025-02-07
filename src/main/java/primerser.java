
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Mario
 */
public class primerser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("username");
        String password = request.getParameter("passwd");
        StringBuffer sb = new StringBuffer();
        sb.append("<br>El nombre de usuario es " + login);
        sb.append("<br>password: " + password);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           if(login.isEmpty() || password.isEmpty()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administracion de Usuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hubo un error, no hay datos ingresados</h1>");
            out.println("</body>");
            out.println("</html>");
           }else if(autenticarUsuario(login, password)==false){
               out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administracion de Usuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Este usuario no existe, vuelva a intentarlo</h1>");
            
            out.println("</body>");
            out.println("</html>");
           
           }else{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administracion de Usuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenido " + sb.toString() + "</h1>");
            out.println("<p>inicio de sesion exitoso</p>");
            out.println("</body>");
            out.println("</html>");
           }
        }
    }

   
   public static boolean autenticarUsuario(String usuario, String clave) {
        String archivo = "C:/Users/Mario/OneDrive/Desktop/usuarios.txt";
 // Archivo donde están los datos

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":"); // Separar usuario y contraseña
                if (partes.length == 2) {
                    String usuarioArchivo = partes[0];
                    String claveArchivo = partes[1];

                    if (usuarioArchivo.equals(usuario) && claveArchivo.equals(clave)) {
                        return true; // Usuario y contraseña correctos
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
            e.printStackTrace();
        }

        return false; // Usuario o contraseña incorrectos
    }
   
        
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
