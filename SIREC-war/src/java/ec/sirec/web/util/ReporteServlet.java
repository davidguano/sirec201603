
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.util;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Darwin Aldas
 */
public class ReporteServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream out = response.getOutputStream();
        PrintWriter out1 = new PrintWriter(out);
        byte[] reporteInforme = (byte[]) request.getSession().getAttribute("reporteInforme");

        try {
            if (reporteInforme != null) {
                if (reporteInforme.length > 900) {
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "inline; filename=Reporte.pdf;");
                    out.write(reporteInforme);
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    //TODO output your page here
                    out1.println("<html>");
                    out1.println("<head>");
                    out1.println("<title>Servlet NewServlet</title>");
                    out1.println("</head>");
                    out1.println("<body>");
                    out1.println("<p><p><p><center><h1>Estimado usuario no existen datos para generar su reporte</h1></center>");
                    out1.println("</body>");
                    out1.println("</html>");
                }
            }
        } finally {
            out1.close();
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
