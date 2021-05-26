
package com.emergentes.controlador;

import com.emergentes.dao.PermisoDAO;
import com.emergentes.dao.PermisoDAOimpl;
import com.emergentes.modelo.Permisos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PermisoControlador", urlPatterns = {"/PermisoControlador"})
public class PermisoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try{
            Permisos per = new Permisos();
            int id;
            PermisoDAO dao = new PermisoDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("permisos", per);
                    request.getRequestDispatcher("frmpermiso.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    per =  dao.getById(id);
                    request.setAttribute("permisos", per);
                    request.getRequestDispatcher("frmpermiso.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("PermisoControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Permisos> lista = dao.getAll();
                    request.setAttribute("permisos",lista);
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
                    break;
            }
            
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        int id_rol = Integer.parseInt(request.getParameter("id_rol"));
        
        Permisos per = new Permisos();
            
        
        per.setId(id);
        per.setId_usuario(id_usuario);
        per.setId_rol(id_rol);
        
        
        PermisoDAO dao = new PermisoDAOimpl();
        
        if (id == 0){
            try {
                // Nuevo registro
                dao.insert(per);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ex.getMessage());
            }
        }
        else{
            try {
                // Edicion de registro
                dao.update(per);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        }
        response.sendRedirect("PermisoControlador");       
    }
}
