package logico.controlador;

import io.javalin.Javalin;
import io.jsonwebtoken.*;
import logico.goniometriaClass.*;
import logico.servicios.DocumentoServicios;
import logico.servicios.PacienteServicios;
import logico.servicios.PreMedidaServicios;
import logico.servicios.TerapiaServicios;
import logico.util.BaseControlador;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RecibirDatosControlador extends BaseControlador {
    public final static String SECRET_KEY = "ghQaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    public List<String> token = new ArrayList<String>();
    public RecibirDatosControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        /**
         * Ejemplo para leer por parametros de consulta (query param)
         * http://localhost:7000/parametros?matricula=20011126&nombre=Carlos%20Camacho
         */


        /**
         * Manejador que se aplica de la ruta /isc415
         */





        app.get("/login", ctx -> {
            ctx.header("Content-Type", "application/json");
            ctx.render("/publico/Formulario-Login/login.html");
        });



        app.get("/verification/", ctx -> {


                System.out.println("llego: "+ctx.queryParam("username"));
                String deco = ctx.queryParam("username");
                System.out.println("que es: " + deco);
                String user = Goniometria.getInstance().getUserEncryptor().decrypt(deco);
                System.out.println("hola:" +user);
                if (Goniometria.getInstance().verificar_cuenta(user) == true){
                    ctx.redirect("/verificationSuccess");
                }




        });


        app.get("/verificationSuccess", ctx -> {
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

            System.out.println(ctx.req.getHeaderNames());

            //listando la informacion.
            if (Goniometria.getInstance().verificacion(user,pass) == true){
                if (Goniometria.getInstance().verificacion_correo(user,pass)==true){




                    String header = "Authorization";
                    String jwt = createJWT(user);



                    ctx.sessionAttribute("User",jwt);
                    ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);

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
            path("/logout", () -> {


                        get(ctx -> {


                            ctx.removeCookie("User");
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");

                        });

                    });

            app.before("/dashboard",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());
                                System.out.println("Nuevo token: " + jwt);

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });
            app.before("/informacion",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });


            path("/dashboard", () -> {



                get(ctx -> {
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        ctx.res.addHeader("Authorization",ctx.cookie("User"));
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());

                        ctx.render("/publico/ProyectoGon/dashboard.html",modelo);
                    } else {ctx.res.addHeader("lola","pepe");
                        System.out.println("hola");
                        ctx.redirect("/login");
                    }
                });
            });
            path("/informacion", () -> {


                get(ctx -> {
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        //modelo.put("especialista", new Especialista(null,null,null,null,null,null,null,null,null,null));
                        //modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(user));
                        //th:text="${especialista.nombre}+' '+${especialista.apellido}"
                        ctx.redirect("/medico.html");
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            app.before("/register",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });


            path("/register", () -> {

                get(ctx -> {




                        String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                        if (ctx.cookie("User") != null) {
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user",decodeJWT(user).getId());
                            ctx.render("/publico/ProyectoGon/registrarPac.html",modelo);
                        } else {
                            ctx.redirect("/login");
                        }





                        });



                post(ctx -> {
                    if(ctx.formParam("documento")!=null){
                        String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user", decodeJWT(user).getId());
                        modelo.put("idPaciente", ctx.formParam("idPaciente"));
                        ctx.render("/publico/ProyectoGon/insertar.html", modelo);

                    }else if(ctx.formParam("idPaciente")!=null){
                            ctx.sessionAttribute("idPaciente",ctx.formParam("idPaciente"));
                            ctx.uploadedFiles("inputFileToLoad").forEach(uploadedFile -> {
                                try {
                                    byte[] bytes = uploadedFile.getContent().readAllBytes();
                                    String encodedString = Base64.getEncoder().encodeToString(bytes);
                                    Documento foto = new Documento(encodedString, uploadedFile.getContentType(), uploadedFile.getFilename(), ctx.formParam("idPaciente"));
                                    DocumentoServicios.getInstance().addDocumento(foto);
                                } catch (IOException d ) {
                                    d.printStackTrace();
                                }
                                ctx.redirect("/profile");
                            });

                    }else {


                        //String pais, String ciudad, String calle, String sector, String n_residencia
                        Direccion dire = new Direccion("Republica Dominicana", ctx.formParam("ciudad"), ctx.formParam("calle"), ctx.formParam("sector"), null);
                        String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));


                        Paciente aux = new Paciente(ctx.formParam("cedula"), user,
                                ctx.formParam("nombre"), ctx.formParam("apellido"), ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                                ctx.formParam("telefono"), dire.getID_Direccion(), ctx.formParam("correo"), ctx.formParam("comentario"));

                        PacienteServicios.getInstance().crearPaciente(aux, dire);

                        if (Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User")) != null) {
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user", decodeJWT(user).getId());
                            ctx.render("/publico/ProyectoGon/dashboard.html", modelo);
                        } else {
                            ctx.redirect("/login");
                        }
                    }
                });
            });

            app.before("/buscarPaciente",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });

            path("/buscarPaciente", () -> {



                get(ctx -> {

                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(decodeJWT(user).getId()));
                        modelo.put("dirigir","/profile");
                        ctx.render("/publico/ProyectoGon/buscarPac.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            app.before("/estadisticas",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");

                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });

            path("/estadisticas", () -> {




                get(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(decodeJWT(user).getId()));
                        modelo.put("dirigir","/estadisticas");
                        ctx.render("/publico/ProyectoGon/buscarPac.html",modelo);


                    } else {
                        ctx.redirect("/login");
                    }
                });

                post(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                        modelo.put("cantMedida", TerapiaServicios.getInstance().cantidadMedida(ctx.formParam("idPaciente")));
                        ctx.render("/publico/ProyectoGon/estadisticas.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            app.before("/buscarMedida",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });

            path("/buscarMedida", () -> {



                post(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaMedida", TerapiaServicios.getInstance().listaMedida(ctx.formParam("idPaciente")));
                        ctx.render("/publico/ProyectoGon/medidas.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });

                get(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaMedida", TerapiaServicios.getInstance().listaMedida(ctx.queryParam("idPaciente")));
                        ctx.render("/publico/ProyectoGon/medidas.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            app.before("/profile",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });

            path("/profile", () -> {
                get(ctx -> {
                    if(ctx.sessionAttribute("idPaciente")!=null){
                        Paciente aux  = PacienteServicios.getInstance().getPaciente(ctx.sessionAttribute("idPaciente"));
                        ctx.req.getSession().removeAttribute("idPaciente");
                        Direccion dire = PacienteServicios.getInstance().getDireccion(aux.getID_Direccion());

                    /*            Paciente aux = new Paciente(ctx.formParam("cedula"),null,
                            ctx.formParam("nombre"), ctx.formParam("apellido"),
                            ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                            ctx.formParam("phone"), dire.getID_Direccion());    */
                        String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                        if (ctx.cookie("User") != null) {
                            Documento aux1 = DocumentoServicios.getInstance().getDocumento(aux.getIdPaciente());
                            try {
                                if (aux1.getBase64()==null){
                                    aux1 = null;
                                }
                            }catch (NullPointerException d){
                                aux1 = new Documento("","","","");
                            }
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user",decodeJWT(user).getId());
                            modelo.put("paciente",aux);
                            modelo.put("direccion",dire);
                            modelo.put("perfil","Editar");
                            modelo.put("preMedida",PreMedidaServicios.getInstance().listaPreMedida(aux.getIdPaciente()));
                            modelo.put("documento",aux1);
                            ctx.render("/publico/ProyectoGon/profile.html",modelo);
                        } else {
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.redirect("/dashboard");
                    }

                });


                post(ctx -> {

                    Paciente aux  = PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente"));
                    Direccion dire = PacienteServicios.getInstance().getDireccion(aux.getID_Direccion());

                    /*            Paciente aux = new Paciente(ctx.formParam("cedula"),null,
                            ctx.formParam("nombre"), ctx.formParam("apellido"),
                            ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                            ctx.formParam("phone"), dire.getID_Direccion());    */
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Documento aux1 = DocumentoServicios.getInstance().getDocumento(aux.getIdPaciente());
                        try {
                            if (aux1.getBase64()==null){
                                aux1 = null;

                            }
                        }catch (NullPointerException d){
                            aux1 = new Documento("","","","");
                        }

                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("paciente",aux);
                        modelo.put("direccion",dire);
                        modelo.put("perfil","Editar");
                        modelo.put("preMedida",PreMedidaServicios.getInstance().listaPreMedida(aux.getIdPaciente()));
                        modelo.put("documento",aux1);
                        ctx.render("/publico/ProyectoGon/profile.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });

            app.before("/RegistrarPreMedida",ctx -> {


                System.out.println(ctx.req.getPathInfo());

                String headerAuth = ctx.req.getHeader("Authorization");
                System.out.println(headerAuth);
                String user = ctx.cookie("User");
                String session = ctx.sessionAttribute("User");
                if (user==null || session == null){

                    System.out.println(Goniometria.getInstance().getUserEncryptor().decrypt(user));
                    ctx.redirect("/login");

                }else{

                    if(Goniometria.getInstance().getUserEncryptor().decrypt(user).equalsIgnoreCase(session)){
                        try {
                            if(isExpirate(decodeJWT(session))==false){
                                String header = "Authorization";
                                String jwt = createJWT(decodeJWT(session).getId());

                                ctx.sessionAttribute("User",jwt);
                                ctx.cookie("User",Goniometria.getInstance().getUserEncryptor().encrypt(jwt),2147483647);
                                String mensaje = String.format("Manejador before aplicando a todas las llamadas: %s, Contexto: %s, Metodo: %s",
                                        ctx.req.getRemoteHost(),
                                        ctx.req.getServletPath(),
                                        ctx.req.getMethod());
                                //
                                System.out.println(mensaje);

                            }else{
                                ctx.req.getSession().invalidate();
                                ctx.redirect("/login");
                            }

                        }catch (ExpiredJwtException e){
                            ctx.req.getSession().invalidate();
                            ctx.redirect("/login");
                        }
                    }else{
                        ctx.req.getSession().invalidate();
                        ctx.redirect("/login");
                    }


                }
            });
            path("/RegistrarPreMedida", () -> {



                post(ctx -> {

                    Paciente aux  = PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente"));


                    /*            Paciente aux = new Paciente(ctx.formParam("cedula"),null,
                            ctx.formParam("nombre"), ctx.formParam("apellido"),
                            ctx.formParam("sexo"), ctx.formParam("fechaNacimiento"),
                            ctx.formParam("phone"), dire.getID_Direccion());    */
                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));

                    if (ctx.cookie("User") != null) {
                        if (ctx.formParam("preMedida").equalsIgnoreCase("Agregar Pre Medida")){
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user",decodeJWT(user).getId());
                            modelo.put("paciente",aux);
                            ctx.render("/publico/ProyectoGon/tomarMedida.html",modelo);
                        }else if (ctx.formParam("preMedida").equalsIgnoreCase("agregar")){
                            PreMedida preMedida = new PreMedida(null,ctx.formParam("medida"),ctx.formParam("lugar"),ctx.formParam("rom"));
                            PreMedidaServicios.getInstance().addPreMedida(preMedida,ctx.formParam("idPaciente"));
                            ctx.sessionAttribute("idPaciente",ctx.formParam("idPaciente"));
                            ctx.redirect("/profile");
                        }

                    } else {
                        ctx.redirect("/login");
                    }
                });
            });


        });
    }



    public static String createJWT(String username) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(username)
                .setIssuedAt(now)
                .setSubject("Goniometria")
                .setIssuer("PUCMM-ITT")
                .signWith(signatureAlgorithm, signingKey);

        // 5 minutes para expiracion
        long expMillis = nowMillis + 300000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        System.out.println("fecha" +now +exp);
        System.out.println("Expiro: "+isExpirate(decodeJWT(builder.compact())));
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static boolean isExpirate(Claims jwt){
        boolean expire = true;

        if (new  Date(System.currentTimeMillis()).after(jwt.getExpiration())){
            return expire;
        }
        return false;
    }

    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();

        return claims;
    }



}
