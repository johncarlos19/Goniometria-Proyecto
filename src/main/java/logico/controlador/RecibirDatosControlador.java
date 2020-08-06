package logico.controlador;

import io.javalin.Javalin;
import logico.goniometriaClass.*;
import logico.util.BaseControlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RecibirDatosControlador extends BaseControlador {

    public RecibirDatosControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        /**
         * Ejemplo para leer por parametros de consulta (query param)
         * http://localhost:7000/parametros?matricula=20011126&nombre=Carlos%20Camacho
         */

        app.before(ctx -> {
            //
            String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                    ctx.req.getRemoteHost(),
                    ctx.req.getServletPath(),
                    ctx.req.getMethod());
            //
            System.out.println(mensaje);
        });

        /**
         * Manejador que se aplica de la ruta /isc415
         */
        app.before("/", ctx -> {
            //

        });


        app.get("/parametros", ctx -> {
            List<String> salida = new ArrayList<>();
            salida.add("Mostrando todos los parametros enviados:");
            //listando la informacion.
            ctx.queryParamMap().forEach((key, lista) -> {
                salida.add(String.format("[%s] = [%s]", key, String.join(",", lista)));
            });
            //
            ctx.result(String.join("\n", salida));
        });

        app.get("/login", ctx -> {
            ctx.render("/publico/Formulario-Login/login.html");
        });


        app.get("/verification/:username", ctx -> {
            String user = ctx.pathParam("username");
            System.out.println("hola:" +user);
            if (Goniometria.getInstance().verificar_cuenta(user) == true){
                ctx.redirect("/verification");
            }


        });


        app.get("/verification", ctx -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("parrafo1","Su cuenta ha sido verificada correctamente");
            ctx.render("/publico/Formulario-Login/confirmation.html",modelo);


        });

        app.post("/parametros", ctx -> {
            List<String> salida = new ArrayList<>();

            //listando la informacion.
            ctx.formParamMap().forEach((key, lista) -> {

                salida.add(0,String.format("[%s] = [%s]", key, String.join(",", lista)));
            });
            salida.add(0, "Mostrando todos la informacion enviada en el cuerpo:");
            //
            System.out.println(salida);
            ctx.result(String.join("\n", salida));
        });


        app.post("/create", ctx -> {


            List<String> salida = new ArrayList<>();
            Create_Person new_person = new Create_Person();
            //listando la informacion.
            ctx.formParamMap().forEach((key, lista) -> {
                new_person.setValue(key,lista.get(0));
                salida.add(0,String.format("[%s] = [%s]", key, String.join(",", lista)));
            });
            salida.add(0, "Mostrando todos la informacion enviada en el cuerpo:");
            //
            System.out.println("entro");
            Cuenta cuenta = new Cuenta(new_person.getUser(), new_person.getEmail(), new_person.getPass(), "administrador");
            Direccion dire = new Direccion(new_person.getPais(), new_person.getCiudad(), new_person.getCalle(), new_person.getSector(), new_person.getNume_residen());
            Especialista especialista = new Especialista(new_person.getCedula(), new_person.getUser(), new_person.getNombre(), new_person.getApellido(), new_person.getSexo(), new_person.getDatedeli(), new_person.getPhone(), dire.getID_Direccion(), new_person.getEspecialidad(), cuenta);
            if (cuenta.isAvailable()==true && especialista.isAvailable() == true) {
                //cuenta.Send_Information();
                dire.Send_Information();
                especialista.register_user();
                System.out.println(salida);
                System.out.println("imprimir lo agregado:" + new_person.getCedula());
                Goniometria.getInstance().send_correo(cuenta.getCorreo(),cuenta.getID_cuenta());
                ctx.redirect("/success");
                //ctx.result(String.join("\n", salida) + "Se ha registrado correctamente");
            }else{
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("adver","Este nombre de usuario o cedula han sido registrado");
                ctx.render("/publico/Formulario-Login/login.html",modelo);
            }
        });
        app.get("/success", ctx -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("parrafo1","Su cuenta se ha registrado Exictosamente");
            modelo.put("parrafo2","Verifique su correo para validar su cuenta");
            ctx.render("/publico/Formulario-Login/confirmation.html",modelo);
        });



        app.post("/login", ctx -> {

            String user = ctx.formParam("user");
            String pass = ctx.formParam("pass");

            //listando la informacion.
            if (Goniometria.getInstance().verificacion(user,pass) == true){
                if (Goniometria.getInstance().verificacion_correo(user,pass)==true){
                    ctx.cookie("User",user,120);
                    ctx.redirect("/dashboard");
                }else {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("adver","Validar su cuenta con el correo enviando");
                    ctx.render("/publico/Formulario-Login/login.html",modelo);
                }

            }else{
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("adver","El nombre de usuario o contrasena son incorrecto");
                ctx.render("/publico/Formulario-Login/login.html",modelo);
            }
        });



        /**
         * En cualquier situación puedo los encabezados de la trama HTTP.
         * http://localhost:7000/leerheaders
         */

        app.get("leerheaders", ctx -> {
            List<String> salida = new ArrayList<>();
            salida.add("Mostrando la informacion enviada en los headers:");
            //listando la informacion.
            ctx.headerMap().forEach((key, valor) -> {
                salida.add(String.format("[%s] = [%s]", key, String.join(",", valor)));
            });
            //
            ctx.result(String.join("\n", salida));
        });

        app.routes(() -> {

            path("/dashboard", () -> {

                get(ctx -> {
                    String user = ctx.cookie("User");
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        ctx.render("/publico/ProyectoGon/dashboard.html",modelo);
                    } else {
                        ctx.redirect("/publico/Formulario-Login/login.html");
                    }
                });
            });
            path("/register", () -> {

                post(ctx -> {
                    Direccion dire = new Direccion(ctx.formParam("country"), ctx.formParam("city"), ctx.formParam("address"), null, null);
                    dire.Send_Information();
                    Paciente aux = new Paciente(ctx.formParam("cedula"),null,
                            ctx.formParam("nombre"), ctx.formParam("apellido"),
                            ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                            ctx.formParam("phone"), dire.getID_Direccion());
                    String user = ctx.cookie("User");
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        ctx.render("/publico/ProyectoGon/dashboard.html",modelo);
                    } else {
                        ctx.redirect("/publico/Formulario-Login/login.html");
                    }
                });
            });



        });
    }


}
