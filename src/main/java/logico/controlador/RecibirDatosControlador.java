package logico.controlador;

import io.javalin.Javalin;
import io.jsonwebtoken.*;
import logico.goniometriaClass.*;
import logico.servicios.*;
import logico.util.BaseControlador;
import logico.util.ErrorServer;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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


        app.exception(Exception.class, (exception, ctx) -> {
            ctx.status(404);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            String mensaje = "<!DOCTYPE html>\n" +
                    "<html lang='en' style='box-sizing: border-box;'>\n" +
                    "<head style='box-sizing: border-box;'>\n" +
                    "    <meta charset='utf-8' style='box-sizing: border-box;'>\n" +
                    "    <title style='box-sizing: border-box;'></title>\n" +
                    "    <meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no' style='box-sizing: border-box;'>\n" +
                    "    <meta name='description' content='' style='box-sizing: border-box;'>\n" +
                    "    <meta name='author' content='Mark Otto, Jacob Thornton, and Bootstrap contributors' style='box-sizing: border-box;'>\n" +
                    "    <meta name='generator' content='Jekyll v4.0.1' style='box-sizing: border-box;'>\n" +
                    "    <!-- Place favicon.ico in the root directory -->\n" +
                    "\n" +
                    "    <!--<link rel='stylesheet' href='bootstrap-4.5.2-dist/css/bootstrap.css1'>-->\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    <style style='box-sizing: border-box;'>\n" +
                    "        /*! CSS Used from: Embedded */\n" +
                    "        .container{max-width:960px;}\n" +
                    "        .card-deck .card{min-width:220px;}\n" +
                    "        /*! CSS Used from: Embedded */\n" +
                    "        *,*::before,*::after{box-sizing:border-box;}\n" +
                    "        body{margin:0;font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';font-size:1rem;font-weight:400;line-height:1.5;color:#212529;text-align:left;background-color:#fff;}\n" +
                    "        h4,h5{margin-top:0;margin-bottom:0.5rem;}\n" +
                    "        p{margin-top:0;margin-bottom:1rem;}\n" +
                    "        a{color:#007bff;text-decoration:none;background-color:transparent;}\n" +
                    "        a:hover{color:#0056b3;text-decoration:underline;}\n" +
                    "        a:not([href]):not([class]){color:inherit;text-decoration:none;}\n" +
                    "        a:not([href]):not([class]):hover{color:inherit;text-decoration:none;}\n" +
                    "        img{vertical-align:middle;border-style:none;}\n" +
                    "        h4,h5{margin-bottom:0.5rem;font-weight:500;line-height:1.2;}\n" +
                    "        h4{font-size:1.5rem;}\n" +
                    "        h5{font-size:1.25rem;}\n" +
                    "        .lead{font-size:1.25rem;font-weight:300;}\n" +
                    "        .container{width:100%;padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto;}\n" +
                    "        @media (min-width: 576px){\n" +
                    "            .container{max-width:540px;}\n" +
                    "        }\n" +
                    "        @media (min-width: 768px){\n" +
                    "            .container{max-width:720px;}\n" +
                    "        }\n" +
                    "        @media (min-width: 992px){\n" +
                    "            .container{max-width:960px;}\n" +
                    "        }\n" +
                    "        @media (min-width: 1200px){\n" +
                    "            .container{max-width:1140px;}\n" +
                    "        }\n" +
                    "        .row{display:-ms-flexbox;display:flex;-ms-flex-wrap:wrap;flex-wrap:wrap;margin-right:-15px;margin-left:-15px;}\n" +
                    "        .col-4,.col{position:relative;width:100%;padding-right:15px;padding-left:15px;}\n" +
                    "        .col{-ms-flex-preferred-size:0;flex-basis:0;-ms-flex-positive:1;flex-grow:1;max-width:100%;}\n" +
                    "        .col-4{-ms-flex:0 0 33.333333%;flex:0 0 33.333333%;max-width:33.333333%;}\n" +
                    "        .btn-danger{color:#fff;background-color:#dc3545;border-color:#dc3545;}\n" +
                    "        .btn-danger:hover{color:#fff;background-color:#c82333;border-color:#bd2130;}\n" +
                    "        .btn-danger:focus{color:#fff;background-color:#c82333;border-color:#bd2130;box-shadow:0 0 0 0.2rem rgba(225, 83, 97, 0.5);}\n" +
                    "        .btn-danger:disabled{color:#fff;background-color:#dc3545;border-color:#dc3545;}\n" +
                    "        .collapse:not(.show){display:none;}\n" +
                    "        .navbar{position:relative;display:-ms-flexbox;display:flex;-ms-flex-wrap:wrap;flex-wrap:wrap;-ms-flex-align:center;align-items:center;-ms-flex-pack:justify;justify-content:space-between;padding:0.5rem 1rem;}\n" +
                    "        .navbar-brand{display:inline-block;padding-top:0.3125rem;padding-bottom:0.3125rem;margin-right:1rem;font-size:1.25rem;line-height:inherit;white-space:nowrap;}\n" +
                    "        .navbar-brand:hover,.navbar-brand:focus{text-decoration:none;}\n" +
                    "        .navbar-collapse{-ms-flex-preferred-size:100%;flex-basis:100%;-ms-flex-positive:1;flex-grow:1;-ms-flex-align:center;align-items:center;}\n" +
                    "        @media (min-width: 768px){\n" +
                    "            .navbar-expand-md{-ms-flex-flow:row nowrap;flex-flow:row nowrap;-ms-flex-pack:start;justify-content:flex-start;}\n" +
                    "            .navbar-expand-md .navbar-collapse{display:-ms-flexbox!important;display:flex!important;-ms-flex-preferred-size:auto;flex-basis:auto;}\n" +
                    "        }\n" +
                    "        .navbar-dark .navbar-brand{color:#fff;}\n" +
                    "        .navbar-dark .navbar-brand:hover,.navbar-dark .navbar-brand:focus{color:#fff;}\n" +
                    "        .card{position:relative;display:-ms-flexbox;display:flex;-ms-flex-direction:column;flex-direction:column;min-width:0;word-wrap:break-word;background-color:#fff;background-clip:border-box;border:1px solid rgba(0, 0, 0, 0.125);border-radius:0.25rem;}\n" +
                    "        .card-body{-ms-flex:1 1 auto;flex:1 1 auto;min-height:1px;padding:1.25rem;}\n" +
                    "        .card-title{margin-bottom:0.75rem;}\n" +
                    "        .card-subtitle{margin-top:-0.375rem;margin-bottom:0;}\n" +
                    "        .card-text:last-child{margin-bottom:0;}\n" +
                    "        .card-header{padding:0.75rem 1.25rem;margin-bottom:0;background-color:rgba(0, 0, 0, 0.03);border-bottom:1px solid rgba(0, 0, 0, 0.125);}\n" +
                    "        .card-header:first-child{border-radius:calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0;}\n" +
                    "        .card-deck .card{margin-bottom:15px;}\n" +
                    "        @media (min-width: 576px){\n" +
                    "            .card-deck{display:-ms-flexbox;display:flex;-ms-flex-flow:row wrap;flex-flow:row wrap;margin-right:-15px;margin-left:-15px;}\n" +
                    "            .card-deck .card{-ms-flex:1 0 0%;flex:1 0 0%;margin-right:15px;margin-bottom:0;margin-left:15px;}\n" +
                    "        }\n" +
                    "        .jumbotron{padding:2rem 1rem;margin-bottom:2rem;background-color:#e9ecef;border-radius:0.3rem;}\n" +
                    "        @media (min-width: 576px){\n" +
                    "            .jumbotron{padding:4rem 2rem;}\n" +
                    "        }\n" +
                    "        .justify-content-start{-ms-flex-pack:start!important;justify-content:flex-start!important;}\n" +
                    "        .fixed-top{position:fixed;top:0;right:0;left:0;z-index:1030;}\n" +
                    "        .shadow-sm{box-shadow:0 0.125rem 0.25rem rgba(0, 0, 0, 0.075)!important;}\n" +
                    "        .my-0{margin-top:0!important;}\n" +
                    "        .my-0{margin-bottom:0!important;}\n" +
                    "        .mb-2{margin-bottom:0.5rem!important;}\n" +
                    "        .mb-3{margin-bottom:1rem!important;}\n" +
                    "        .mb-4{margin-bottom:1.5rem!important;}\n" +
                    "        .text-left{text-align:left!important;}\n" +
                    "        .text-center{text-align:center!important;}\n" +
                    "        .font-weight-normal{font-weight:400!important;}\n" +
                    "        .text-muted{color:#6c757d!important;}\n" +
                    "        @media print{\n" +
                    "            *,*::before,*::after{text-shadow:none!important;box-shadow:none!important;}\n" +
                    "            a:not(.btn){text-decoration:underline;}\n" +
                    "            img{page-break-inside:avoid;}\n" +
                    "            p{orphans:3;widows:3;}\n" +
                    "            body{min-width:992px!important;}\n" +
                    "            .container{min-width:992px!important;}\n" +
                    "            .navbar{display:none;}\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "</head>\n" +
                    "<body style='min-height: 75rem;padding-top: 4.5rem;box-sizing: border-box;margin: 0;font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;;font-size: 1rem;font-weight: 400;line-height: 1.5;color: #212529;text-align: left;background-color: #fff;'>\n" +
                    "\n" +
                    "<div class='navbar navbar-expand-md navbar-dark fixed-top ' style='background-color: #97ddee !important;box-sizing: border-box;'>\n" +
                    "  <span class='navbar-brand' style='box-sizing: border-box;'>\n" +
                    "    <img class='sp-default-logo' src='https://app1.goniometer-exoglove.me/img/Xtreme%20Sport%20Channel%20Logo.gif' alt='Televiaducto' width='160' style='box-sizing: border-box;vertical-align: middle;border-style: none;'>\n" +
                    "  </span>\n" +
                    "\n" +
                    "    <div class='collapse navbar-collapse' id='navbarCollapse' style='box-sizing: border-box;'>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<br style='box-sizing: border-box;'>\n" +
                    "<div role='main' class='container' style='box-sizing: border-box;max-width: 960px;width: 100%;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;'>\n" +
                    "\n" +
                    "\n" +
                    "    <div class='jumbotron' style='box-sizing: border-box;'>\n" +
                    "\n" +
                    "        <div class='card' style='box-sizing: border-box;'>\n" +
                    "            <div class='card-body' style='box-sizing: border-box;'>\n" +
                    "                <h4 class='card-title' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.5rem;'>Alert</h4>\n" +
                    "                <h5 class='card-subtitle mb-2 text-muted' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>Error en el servidor</h5>\n" +
                    "\n" +
                    "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
                    "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
                    "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>Severity: </h5>\n" +
                    "                    </div>\n" +
                    "                    <div class='col' style='box-sizing: border-box;'>\n" +
                    "                        <p class='card-text lead btn-danger' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>Critical</p>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
                    "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
                    "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>PATH: </h5>\n" +
                    "                    </div>\n" +
                    "                    <div class='col' style='box-sizing: border-box;'>\n" +
                    "                        <p class='card-text lead' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>" +
                    ctx.path()+
                    "</p>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
                    "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
                    "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>IP ADDRESS: </h5>\n" +
                    "                    </div>\n" +
                    "                    <div class='col' style='box-sizing: border-box;'>\n" +
                    "                        <p class='card-text lead' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>" +
                    ctx.ip()+
                    "</p>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
                    "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
                    "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>METHOD: </h5>\n" +
                    "                    </div>\n" +
                    "                    <div class='col' style='box-sizing: border-box;'>\n" +
                    "                        <p class='card-text lead' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>" +
                    ctx.method()+
                    "</p>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
                    "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
                    "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>Timestamp: </h5>\n" +
                    "                    </div>\n" +
                    "                    <div class='col' style='box-sizing: border-box;'>\n" +
                    "                        <p class='card-text lead' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>" +
                    Goniometria.getInstance().getTimestamp()+
                    "</p>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "\n" +
                    "\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <br style='box-sizing: border-box;'>\n" +
                    "        <div class='' style='box-sizing: border-box;'>\n" +
                    "            <div class='card-deck mb-3 text-center' style='box-sizing: border-box;'>\n" +
                    "\n" +
                    "                <div class='card mb-4 shadow-sm' style='box-sizing: border-box;min-width: 220px;'>\n" +
                    "                    <div class='card-header' style='box-sizing: border-box;'>\n" +
                    "                        <h4 class='my-0 font-weight-normal text-left' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.5rem;'>" +
                    exception.getClass().getName() +
                    "<a style='box-sizing: border-box;color: inherit;text-decoration: none;background-color: transparent;'></a></h4>\n" +
                    "                    </div>\n" +
                    "                    <div class='card-body ' style='box-sizing: border-box;'>\n" +
                    "                        <div class='justify-content-start' style='box-sizing: border-box;'>\n" +
                    "\n" +
                    "                            <div class='row text-left' style='box-sizing: border-box;'>\n" +
                    "                                <div class='col' style='box-sizing: border-box;'>\n" +
                    "                                    <p class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;'>" +
                    sw.toString() +
                    "</p>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "\n" +
                    "\n" +
                    "                    </div>\n" +
                    "\n" +
                    "                </div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "\n" +
                    "\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";

            Goniometria.getInstance().send_correo_online("joanelvis24@gmail.com",mensaje,"Error Servidor");
            Goniometria.getInstance().send_correo_online("johncarlos1943@gmail.com",mensaje,"Error Servidor");

            exception.printStackTrace();
            ctx.redirect("/error404");
        });
        app.get("/error404", ctx -> {

            ctx.render("/publico/ProyectoGon/Error404.html");
        });

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
                        System.out.println("direccion"+EspecialistaServicios.getInstance().getEspecialista(decodeJWT(user).getId()).getDireccion().getCiudad());
                        modelo.put("especialista", EspecialistaServicios.getInstance().getEspecialista(decodeJWT(user).getId()));
                        //modelo.put("especialista", new Especialista(null,null,null,null,null,null,null,null,null,null));
                        //modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(user));
                        //th:text="${especialista.nombre}+' '+${especialista.apellido}"
                        ctx.render("/publico/ProyectoGon//medico.html", modelo);
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

            app.before("/hojaEvolucion",ctx -> {


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

            path("/hojaEvolucion", () -> {




                get(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(decodeJWT(user).getId()));
                        modelo.put("dirigir","/hojaEvolucion");
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
//                        modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                        System.out.println("paciente"+ctx.formParam("idPaciente"));
                        modelo.put("terapia", TerapiaServicios.getInstance().listaTerapiasintIDEvo(ctx.formParam("idPaciente")));
                        ctx.render("/publico/ProyectoGon/completarEvo.html",modelo);
                    } else {
                        ctx.redirect("/login");
                    }
                });
            });







            app.before("/saveformulario",ctx -> {


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

            path("/saveformulario", () -> {




                get(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(decodeJWT(user).getId()));
                        modelo.put("dirigir","/hojaEvolucion");
                        modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                        System.out.println("paciente"+ctx.formParam("idPaciente"));
                        ctx.render("/publico/ProyectoGon/CompletarFormulario.html",modelo);


                    } else {
                        ctx.redirect("/login");
                    }
                });

                post(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {

                        if (ctx.formParam("dirigir")!=null){


                                    FormularioCirugia formularioCirugia = new FormularioCirugia(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(formulario_cirugia.idformulario_cirugia) from goniometria.formulario_cirugia","FCI-",20)
                                ,ctx.formParam("tipo_cirugia")
                                ,ctx.formParam("fecha_cirugia")
                                ,ctx.formParam("hospital_cirugia")
                                ,null);

                        FormularioPruebas formularioPruebas = null;

                        formularioPruebas = new FormularioPruebas(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(formulario_pruebas.idformulario_pruebas) from goniometria.formulario_pruebas","FPR-",20)
                                ,ctx.formParam("tipo_prueba")
                                ,ctx.formParam("fecha_prueba")
                                ,ctx.formParam("hospital_prueba"));

                        PreDiagnostico preDiagnostico = new PreDiagnostico(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(pre_diagnostico.idpre_diagnostico) from goniometria.pre_diagnostico","PDI-",20)
                                ,formularioCirugia.getIdformulario_cirugia()
                                ,formularioPruebas.getIdformulario_pruebas()

                                ,ctx.formParam("molestias_zona")
                                ,ctx.formParam("tipo_lesion")
                                ,ctx.formParam("valoracion_dolor")
                                ,ctx.formParam("sintomas_asociados")
                                ,ctx.formParam("fecha_diagnostico")
                                ,ctx.formParam("hospital_pre")
                                ,ctx.formParam("tipo_prueba"));
                        EstadoSalud estadoSalud = new EstadoSalud(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(estado_salud.idestado_salud) from goniometria.estado_salud","ESD-",20)
                                ,ctx.formParam("condicion")
                                ,null
                                ,ctx.formParam("dificultad")
                                ,ctx.formParam("molestia_dolor")
                                ,ctx.formParam("enfermedades")
                                ,ctx.formParam("dieta_pre")
                                ,ctx.formParam("habitos_toxicos")
                                ,ctx.formParam("estres_nivel"));
                        Medicamentos medicamentos = new Medicamentos(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(medicamentos.idmedicamentos) from goniometria.medicamentos","MDC-",20)
                                ,ctx.formParam("tratamiento")
                                ,ctx.formParam("dosis")
                                ,ctx.formParam("efectos")
                                ,ctx.formParam("tiempo")
                                ,ctx.formParam("revision_medico")
                                ,ctx.formParam("medicamento_pre")
                        );

                        PreguntasGenerales preguntasGenerales = new PreguntasGenerales(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(preguntas_generales.idpreguntas_generales) from goniometria.preguntas_generales","PRG-",20)
                                ,ctx.formParam("medicamento_pre")
                                ,ctx.formParam("enfermedades")
                                ,null
                                ,ctx.formParam("habitos_toxicos")
                                ,ctx.formParam("dieta_pre")
                                ,ctx.formParam("posicion")
                                ,ctx.formParam("actividades")
                                ,ctx.formParam("accidentes")
                                ,medicamentos.getIdmedicamentos()
                                , estadoSalud.getIdestado_salud()
                                ,preDiagnostico.getIdpre_diagnostico());
                        String haymenor = null;
                        FormularioMenor formularioMenor = null;

                        if (!ctx.formParam("nombre_padre" ).equalsIgnoreCase("") && !ctx.formParam("apellido_padre").equalsIgnoreCase("") && !ctx.formParam("seguro_padre").equalsIgnoreCase("")){
                            haymenor = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(formulario_menor.ID_fmenor) from goniometria.formulario_menor","FOM-",20);
                            formularioMenor = new FormularioMenor(haymenor
                                    ,ctx.formParam("nombre_padre")
                                    ,ctx.formParam("apellido_padre")
                                    ,ctx.formParam("relacion_paciente")
                                    ,ctx.formParam("fechanac_padre")
                                    ,ctx.formParam("seguro_padre")
                                    ,ctx.formParam("id_padre"));
                        }


                        Formulario formulario = new Formulario(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(formulario.ID_formulario) from goniometria.formulario","FOR-",20)
                                ,ctx.formParam("seguro_social")
                                ,null
                                ,ctx.formParam("altura")
                                ,ctx.formParam("peso")
                                ,ctx.formParam("ocupacion")
                                ,null
                                ,null
                                ,ctx.formParam("idPaciente")
                                ,haymenor
                                ,preguntasGenerales.getIdpreguntas_generales());

                        FormularioCirugiaServicios.getInstance().addFormularioCirugia(formularioCirugia);
                        FormularioPruebasServicios.getInstance().addFormularioPruebas(formularioPruebas);
                        PreDiagnosticoServicios.getInstance().addPreDiagnostico(preDiagnostico);
                        MedicamentosServicios.getInstance().addMedicamentos(medicamentos);
                        EstadoSaludServicios.getInstance().addEstadoSalud(estadoSalud);
                        PreguntasGeneralesServicios.getInstance().addPreguntasGenerales(preguntasGenerales);
                        if (formulario.getID_fmenor()==null){
                            FormularioServicios.getInstance().addFormulario(formulario);

                        }else {
                            FormularioMenorServicios.getInstance().addFormularioMenor(formularioMenor);
                            FormularioServicios.getInstance().addFormulario(formulario);
                        }

                        ctx.redirect("/dashboard");


                        }else{
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user",decodeJWT(user).getId());
                             modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                            System.out.println("paciente"+ctx.formParam("idPaciente"));
                            ctx.render("/publico/ProyectoGon/CompletarFormulario.html",modelo);
                        }











                    } else {
                        ctx.redirect("/login");
                    }
                });
            });






            app.before("/evolucion",ctx -> {


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






            path("/evolucion", () -> {




                get(ctx -> {


                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(decodeJWT(user).getId()));
                        modelo.put("dirigir","/evolucion");
                        ctx.render("/publico/ProyectoGon/buscarPac.html",modelo);


                    } else {
                        ctx.redirect("/login");
                    }
                });

                post(ctx -> {




                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {


                        if (ctx.formParam("action") != null){
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user",decodeJWT(user).getId());
                        modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                            System.out.println("paciente"+ctx.formParam("idPaciente"));
                            modelo.put("listaEvolucion", EvolucionServicio.getInstance().terapiaEvolucionArrayList(ctx.formParam("idPaciente")));
                            ctx.render("/publico/ProyectoGon/BuscarEvo.html",modelo);
                        }else {
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("user",decodeJWT(user).getId());
                            modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                            System.out.println("paciente"+ctx.formParam("idPaciente"));
                            modelo.put("evolucion", EvolucionServicio.getInstance().getEvolucion(ctx.formParam("idEvolucion")));
                            modelo.put("terapia", TerapiaServicios.getInstance().listaTerapiaConEvo(ctx.formParam("idPaciente"),ctx.formParam("idEvolucion")));
                            ctx.render("/publico/ProyectoGon/HojaEvo.html",modelo);
                        }



                    } else {
                        ctx.redirect("/login");
                    }
                });
            });




            app.before("/uploadEvlucion",ctx -> {


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

            path("/uploadEvlucion", () -> {




//                get(ctx -> {
//
//
//                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
//                    if (ctx.cookie("User") != null) {
//                        Map<String, Object> modelo = new HashMap<>();
//                        modelo.put("user",decodeJWT(user).getId());
//                        modelo.put("listaPaciente", PacienteServicios.getInstance().listaPaciente(decodeJWT(user).getId()));
//                        modelo.put("dirigir","/hojaEvolucion");
//                        ctx.render("/publico/ProyectoGon/buscarPac.html",modelo);
//
//
//                    } else {
//                        ctx.redirect("/login");
//                    }
//                });

                post(ctx -> {
                    ArrayList<String> list = new ArrayList<String>(ctx.formParams("terapiaID"));
                    Evolucion evolucion;
                    try {
                        if(ctx.formParam("retraso")==null || ctx.formParam("retraso").equals("")){
                            evolucion = new Evolucion(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(evolucion_fisioteraputica.idEvolucion_fisioteraputica) from goniometria.evolucion_fisioteraputica","EVO-",20),
                                    ctx.formParam("respuestaTerapia"),
                                    ctx.formParam("name"),
                                    ctx.formParam("profesion"),
                                    ctx.formParam("actividad"),
                                    ctx.formParam("objetivo"),
                                    ctx.formParam("razonamiento"),
                                    ctx.formParam("actividadMeta"),
                                    null,
                                    ctx.formParam("pulso"),
                                    ctx.formParam("temperatura"),
                                    ctx.formParam("presion"),
                                    null,list);
                        }else{
                            evolucion = new Evolucion(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(evolucion_fisioteraputica.idEvolucion_fisioteraputica) from goniometria.evolucion_fisioteraputica","EVO-",20),
                                    ctx.formParam("respuestaTerapia"),
                                    ctx.formParam("name"),
                                    ctx.formParam("profesion"),
                                    ctx.formParam("actividad2"),
                                    ctx.formParam("objetivo"),
                                    ctx.formParam("razonamiento"),
                                    ctx.formParam("actividadMeta2"),
                                    ctx.formParam("retraso"),
                                    ctx.formParam("pulso"),
                                    ctx.formParam("temperatura"),
                                    ctx.formParam("presion"),
                                    null,list);
                        }
                    }catch (NullPointerException e){
                        evolucion = new Evolucion(Goniometria.getInstance().return_ID_Generate_Max_Id("select max(evolucion_fisioteraputica.idEvolucion_fisioteraputica) from goniometria.evolucion_fisioteraputica","EVO-",20),
                                ctx.formParam("respuestaTerapia"),
                                ctx.formParam("name"),
                                ctx.formParam("profesion"),
                                ctx.formParam("actividad"),
                                ctx.formParam("objetivo"),
                                ctx.formParam("razonamiento"),
                                ctx.formParam("actividadMeta"),
                                null,
                                ctx.formParam("pulso"),
                                ctx.formParam("temperatura"),
                                ctx.formParam("presion"),
                                null,list);
                    }


                    EvolucionServicio.getInstance().addEvolucion(evolucion);





                    String user = Goniometria.getInstance().getUserEncryptor().decrypt(ctx.cookie("User"));
                    if (ctx.cookie("User") != null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("user",decodeJWT(user).getId());
//                        modelo.put("paciente", PacienteServicios.getInstance().getPaciente(ctx.formParam("idPaciente")));
                        System.out.println("paciente"+ctx.formParam("idPaciente"));
//                        modelo.put("terapia", TerapiaServicios.getInstance().listaTerapiasintIDEvo(ctx.formParam("idPaciente")));
//                        ctx.render("/publico/ProyectoGon/completarEvo.html",modelo);
                        ctx.redirect("/dashboard");
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

                            Formulario formulario = FormularioServicios.getInstance().getFormulario(aux.getIdPaciente());
                            FormularioMenor formularioMenor = new FormularioMenor();
                            if(formulario.getID_fmenor() != null){
                                formularioMenor = FormularioMenorServicios.getInstance().getFormularioMenor(formulario.getID_fmenor());
                            }

                            PreguntasGenerales preguntasGenerales = PreguntasGeneralesServicios.getInstance().getPreguntasGenerales(formulario.getIdpreguntas_generales());
                            Medicamentos medicamentos = MedicamentosServicios.getInstance().getMedicamentos(preguntasGenerales.getIdmedicamentos());
                            EstadoSalud estadoSalud = EstadoSaludServicios.getInstance().getEstadoSalud(preguntasGenerales.getIdestado_salud());
                            PreDiagnostico preDiagnostico = PreDiagnosticoServicios.getInstance().getPreDiagnostico(preguntasGenerales.getIdpre_diagnostico());
                            FormularioPruebas formularioPruebas = FormularioPruebasServicios.getInstance().getFormularioPruebas(preDiagnostico.getIdformulario_pruebas());
                            FormularioCirugia formularioCirugia = FormularioCirugiaServicios.getInstance().getFormularioCirugia(preDiagnostico.getIdformulario_cirugia());








                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("formularioCirugia",formularioCirugia);
                            modelo.put("formularioPruebas",formularioPruebas);
                            modelo.put("preDiagnostico",preDiagnostico);
                            modelo.put("estadoSalud",estadoSalud);
                            modelo.put("medicamentos",medicamentos);
                            modelo.put("preguntasGenerales",preguntasGenerales);
                            modelo.put("formularioMenor",formularioMenor);
                            modelo.put("formulario",formulario);


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


                        Formulario formulario = FormularioServicios.getInstance().getFormulario(aux.getIdPaciente());
                        FormularioMenor formularioMenor = new FormularioMenor();
                        if(formulario.getID_fmenor() != null){
                            formularioMenor = FormularioMenorServicios.getInstance().getFormularioMenor(formulario.getID_fmenor());
                        }

                        PreguntasGenerales preguntasGenerales = PreguntasGeneralesServicios.getInstance().getPreguntasGenerales(formulario.getIdpreguntas_generales());
                        Medicamentos medicamentos = MedicamentosServicios.getInstance().getMedicamentos(preguntasGenerales.getIdmedicamentos());
                        EstadoSalud estadoSalud = EstadoSaludServicios.getInstance().getEstadoSalud(preguntasGenerales.getIdestado_salud());
                        PreDiagnostico preDiagnostico = PreDiagnosticoServicios.getInstance().getPreDiagnostico(preguntasGenerales.getIdpre_diagnostico());
                        FormularioPruebas formularioPruebas = FormularioPruebasServicios.getInstance().getFormularioPruebas(preDiagnostico.getIdformulario_pruebas());
                        FormularioCirugia formularioCirugia = FormularioCirugiaServicios.getInstance().getFormularioCirugia(preDiagnostico.getIdformulario_cirugia());



                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("formularioCirugia",formularioCirugia);
                        modelo.put("formularioPruebas",formularioPruebas);
                        modelo.put("preDiagnostico",preDiagnostico);
                        modelo.put("estadoSalud",estadoSalud);
                        modelo.put("medicamentos",medicamentos);
                        modelo.put("preguntasGenerales",preguntasGenerales);
                        modelo.put("formularioMenor",formularioMenor);
                        modelo.put("formulario",formulario);
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
                            modelo.put("tipoMedida","/RegistrarPreMedida");
                            modelo.put("boton","Agregar");
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
        long expMillis = nowMillis + 1000000;
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
