package web;

import datos.ClientesDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kentucky
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.err.println("ERRORES LISTA");

        List<Cliente> clientes = new ClientesDaoJDBC().listar();
        /* System.err.println("ERRORES");
        System.out.println("Clientes = " + clientes);
        request.setAttribute("clientes", clientes);
        request.setAttribute("totalClientes", clientes.size());
        request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        request.getRequestDispatcher("Clientes.jsp").forward(request, response);
         */
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    //pasamos los objetos "request y response" para acceder a su informacion
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    //pasamos los objetos "request y response" para acceder a su informacion
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);

        }

    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;

    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Cliente> clientes = new ClientesDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        //creamos objeto sission
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("Clientes.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recogemos el valorsito del parametro accion y lo guardamos en una varable llamada accion
        String accion = request.getParameter("accion");

        //Si el valor del parámetro enviado en la petición POST es insertar, llamaremos en nuestro servlet a un método “insertarCliente()”:
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    //pasamos los objetos request y response para acceder a su info
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    //pasamos los objetos request y response para acceder a su info
                    this.modificarCliente(request, response);
                    break;

                default:
                    this.accionDefault(request, response);
            }

        } else {
            this.accionDefault(request, response);

        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//recuperamos la info del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        //el metodo getParameter devuelv eun String, por lo que tenemos un problema con el saldo
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        System.out.println("Saldo es "+ saldoString);
        //si saldo no es null o una cadena vacia convetimos el string a double
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
            System.out.println("saldo es " + saldo);
        }
        //creamos el objeto del cliente (MODLO). No id, genera auto
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        //Insertamos objeto en la bbdd
        //la llamada al metodo insertar nos devolvera un entero que recogeremos
        //en registrosModificados y asi sabremos si se hac procesado todo bien

        int registrosModificados = new ClientesDaoJDBC().insertar(cliente);
        System.out.println("Registros Modificados =>" + registrosModificados);

        this.accionDefault(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //recuperamos de id clientte => recorda que request.getParameter deviuelve un string
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClientesDaoJDBC().encontrar(new Cliente(idCliente));
        //tenemos que darle un scope al objeto cliente, por ejem un request
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/Cliente/EditarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;

        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);

            //Creamos el objeto de cliente (MODELO). Recordad, ahora si insertamos id
            Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

            //Modificamos objetos en la bbdd
            int registrosModificados = new ClientesDaoJDBC().actualizar(cliente);
            System.out.println("Registros modificados => " + registrosModificados);

            //Redirigimos el flujo a accion por default para ir a la pantalla ppal
            this.accionDefault(request, response);
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        Cliente cliente = new Cliente(idCliente);
        
        //Eliminamos objeto en nuestra bbdd
        int registrosModificados = new ClientesDaoJDBC().eliminar(cliente);
        System.out.println("Registro modificiados=>" + registrosModificados);
    
    //redirigimos el flujo
    this.accionDefault(request, response);
    }

}
