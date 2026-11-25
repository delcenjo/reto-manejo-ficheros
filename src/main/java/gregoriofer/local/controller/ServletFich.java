package gregoriofer.local.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletFich
 */
@WebServlet("/ServletFich")
public class ServletFich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFich() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		 String formato = request.getParameter("formato");
	     String operacion = request.getParameter("operacion");
	     
	     //Validación de que ha elegido formato y operación
	        if (formato == null || formato.trim().isEmpty() || operacion == null || operacion.trim().isEmpty()) {
	           
	        	request.setAttribute("mensajeError", "(*) Los campos no pueden estar vacios");
	            request.getRequestDispatcher("TratamientoFich.jsp").forward(request, response);
	            return;
	        
	        }
	     
	        String[] nuevosDatos = new String[6];
	     
	        
	        //Si elige escritura revisa que los datos no esten vacios
	        if ("escritura".equalsIgnoreCase(operacion)) {
	        	
	            for (int i = 0; i < 6; i++) {
	            	
	                String valor = request.getParameter("dato" + (i + 1));
	                
	                if (valor == null || valor.trim().isEmpty()) {
	                	
	                    request.setAttribute("mensajeError", "(*) Los campos no pueden estar vacios");
	                    request.getRequestDispatcher("TratamientoFich.jsp").forward(request, response);
	                    return;
	                    
	                }
	                nuevosDatos[i] = valor.trim(); //Guarda datos
	            }
	        }
	        
	        
	        
	        
	        
	        
	        
		
	}

}
