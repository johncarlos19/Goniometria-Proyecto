package logico;


import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import logico.controlador.RecibirDatosControlador;
import logico.goniometriaClass.Goniometria;

import java.sql.SQLException;
//hol

public class Main {
    public static void main(String[] args) throws SQLException {
        String p = null;
        //create connection for a server installed in localhost, with a user "root" with no password
        Goniometria.getInstance().Start_Connection_DataBase("localhost","3306","goniometria","root","castillo30");
       // Goniometria.getInstance().Execute_query("select * from actor");
        //System.out.println(Goniometria.getInstance().return_ID_Generate("select count(*) from direccion","DIR-",13));
        //Goniometria.getInstance().close_connectio();

        //Goniometria.getInstance().send_correo("johncarlos1943@gmail.com","johncarlos1943");

        String mensaje = "Hola Mundo en Javalin :-D";
        System.out.println(mensaje);

        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/publico"); //desde la carpeta de resources
            config.addStaticFiles("/publico/Formulario-Login");
           // config.addStaticFiles("/publico/AdminLTE-3.0.5");
            config.registerPlugin(new RouteOverviewPlugin("/rutas")); //aplicando plugins de las rutas
        }).start(getHerokuAssignedPort());

        //creando el manejador
        app.get("/", ctx -> ctx.render("/publico/index.html"));
        //aplicando las rutas para el procesamiento de los datos.
        new RecibirDatosControlador(app).aplicarRutas();

    }

    /**
     * Metodo para indicar el puerto en Heroku
     * @return
     *
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }


}
