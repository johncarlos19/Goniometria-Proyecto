package logico.controlador;

import io.javalin.Javalin;
import logico.goniometriaClass.*;
import logico.servicios.PacienteServicios;
import logico.servicios.TerapiaServicios;
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




        app.get("/login", ctx -> {
            ctx.render("/publico/Formulario-Login/login.html");
        });



        app.get("/verification/:username", ctx -> {
            String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.pathParam("username"));
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

                    ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(user),120);
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



        app.routes(() -> {

            path("/dashboard", () -> {

                get(ctx -> {
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        ctx.render("/publico/ProyectoGon/dashboard.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });
            path("/register", () -> {
                get(ctx -> {
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        ctx.render("/publico/ProyectoGon/registrarPac.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }

                        });



                post(ctx -> {
                    Direccion dire = new Direccion(null, ctx.formParam("municipio"), ctx.formParam("calle"), ctx.formParam("sector"), null);
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));


                    Paciente aux = new Paciente(ctx.formParam("cedula"),user,
                            ctx.formParam("nombre"), ctx.formParam("apellido"), ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                            ctx.formParam("telefono"), dire.getID_Direccion(), ctx.formParam("correo"), ctx.formParam("comentario") );

                    PacienteServicios.getInstance().crearPaciente(aux, dire);

                    if (Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User")) != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        ctx.render("/publico/ProyectoGon/dashboard.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            path("/buscarPaciente", () -> {

                get(ctx -> {

                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(user));
                        ctx.render("/publico/ProyectoGon/buscarPac.html",modelo);
                    } else {
                        ctx.redirect("/publico/Formulario-Login/login.html");
                    }
                });
            });

            path("/buscarMedida", () -> {

                post(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        modelo.put("listaMedida", TerapiaServicios.getInstance().listaMedida(ctx.formParam("idPaciente")));
                        ctx.render("/publico/ProyectoGon/medidas.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            path("/profile", () -> {

                post(ctx -> {

                    Paciente aux  = PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente"));
                    Direccion dire = PacienteServicios.getInstance().getDireccion(aux.getID_Direccion());

                    /*            Paciente aux = new Paciente(ctx.formParam("cedula"),null,
                            ctx.formParam("nombre"), ctx.formParam("apellido"),
                            ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                            ctx.formParam("phone"), dire.getID_Direccion());    */
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",user);
                        modelo.put("paciente",aux);
                        modelo.put("direccion",dire);
                        modelo.put("perfil","Editar");
                        ctx.render("/publico/ProyectoGon/profile.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });


        });
    }


}
