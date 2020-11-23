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
        Goniometria.getInstance().Start_Connection_DataBase("goniometriaproyect.cm31gjdb0ov8.us-east-1.rds.amazonaws.com","3306","goniometria?characterEncoding=latin1","johncarlos1943","castillo30");
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
            config.addStaticFiles("/publico/ProyectoGon");
            config.registerPlugin(new RouteOverviewPlugin("/rutas")); //aplicando plugins de las rutas
        }).start(getHerokuAssignedPort());

        //creando el manejador
        app.get("/", ctx -> ctx.render("/publico/index.html"));
        //aplicando las rutas para el procesamiento de los datos.
        new RecibirDatosControlador(app).aplicarRutas();
//        Goniometria.getInstance().send_correo_online("johncarlos1943@gmail.com","<!DOCTYPE html>\n" +
//                "<html lang='en' style='box-sizing: border-box;'>\n" +
//                "<head style='box-sizing: border-box;'>\n" +
//                "    <meta charset='utf-8' style='box-sizing: border-box;'>\n" +
//                "    <title style='box-sizing: border-box;'></title>\n" +
//                "    <meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no' style='box-sizing: border-box;'>\n" +
//                "    <meta name='description' content='' style='box-sizing: border-box;'>\n" +
//                "    <meta name='author' content='Mark Otto, Jacob Thornton, and Bootstrap contributors' style='box-sizing: border-box;'>\n" +
//                "    <meta name='generator' content='Jekyll v4.0.1' style='box-sizing: border-box;'>\n" +
//                "    <!-- Place favicon.ico in the root directory -->\n" +
//                "\n" +
//                "    <!--<link rel='stylesheet' href='bootstrap-4.5.2-dist/css/bootstrap.css1'>-->\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "    <style style='box-sizing: border-box;'>\n" +
//                "        /*! CSS Used from: Embedded */\n" +
//                "        .container{max-width:960px;}\n" +
//                "        .card-deck .card{min-width:220px;}\n" +
//                "        /*! CSS Used from: Embedded */\n" +
//                "        *,*::before,*::after{box-sizing:border-box;}\n" +
//                "        body{margin:0;font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';font-size:1rem;font-weight:400;line-height:1.5;color:#212529;text-align:left;background-color:#fff;}\n" +
//                "        h4,h5{margin-top:0;margin-bottom:0.5rem;}\n" +
//                "        p{margin-top:0;margin-bottom:1rem;}\n" +
//                "        a{color:#007bff;text-decoration:none;background-color:transparent;}\n" +
//                "        a:hover{color:#0056b3;text-decoration:underline;}\n" +
//                "        a:not([href]):not([class]){color:inherit;text-decoration:none;}\n" +
//                "        a:not([href]):not([class]):hover{color:inherit;text-decoration:none;}\n" +
//                "        img{vertical-align:middle;border-style:none;}\n" +
//                "        h4,h5{margin-bottom:0.5rem;font-weight:500;line-height:1.2;}\n" +
//                "        h4{font-size:1.5rem;}\n" +
//                "        h5{font-size:1.25rem;}\n" +
//                "        .lead{font-size:1.25rem;font-weight:300;}\n" +
//                "        .container{width:100%;padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto;}\n" +
//                "        @media (min-width: 576px){\n" +
//                "            .container{max-width:540px;}\n" +
//                "        }\n" +
//                "        @media (min-width: 768px){\n" +
//                "            .container{max-width:720px;}\n" +
//                "        }\n" +
//                "        @media (min-width: 992px){\n" +
//                "            .container{max-width:960px;}\n" +
//                "        }\n" +
//                "        @media (min-width: 1200px){\n" +
//                "            .container{max-width:1140px;}\n" +
//                "        }\n" +
//                "        .row{display:-ms-flexbox;display:flex;-ms-flex-wrap:wrap;flex-wrap:wrap;margin-right:-15px;margin-left:-15px;}\n" +
//                "        .col-4,.col{position:relative;width:100%;padding-right:15px;padding-left:15px;}\n" +
//                "        .col{-ms-flex-preferred-size:0;flex-basis:0;-ms-flex-positive:1;flex-grow:1;max-width:100%;}\n" +
//                "        .col-4{-ms-flex:0 0 33.333333%;flex:0 0 33.333333%;max-width:33.333333%;}\n" +
//                "        .btn-danger{color:#fff;background-color:#dc3545;border-color:#dc3545;}\n" +
//                "        .btn-danger:hover{color:#fff;background-color:#c82333;border-color:#bd2130;}\n" +
//                "        .btn-danger:focus{color:#fff;background-color:#c82333;border-color:#bd2130;box-shadow:0 0 0 0.2rem rgba(225, 83, 97, 0.5);}\n" +
//                "        .btn-danger:disabled{color:#fff;background-color:#dc3545;border-color:#dc3545;}\n" +
//                "        .collapse:not(.show){display:none;}\n" +
//                "        .navbar{position:relative;display:-ms-flexbox;display:flex;-ms-flex-wrap:wrap;flex-wrap:wrap;-ms-flex-align:center;align-items:center;-ms-flex-pack:justify;justify-content:space-between;padding:0.5rem 1rem;}\n" +
//                "        .navbar-brand{display:inline-block;padding-top:0.3125rem;padding-bottom:0.3125rem;margin-right:1rem;font-size:1.25rem;line-height:inherit;white-space:nowrap;}\n" +
//                "        .navbar-brand:hover,.navbar-brand:focus{text-decoration:none;}\n" +
//                "        .navbar-collapse{-ms-flex-preferred-size:100%;flex-basis:100%;-ms-flex-positive:1;flex-grow:1;-ms-flex-align:center;align-items:center;}\n" +
//                "        @media (min-width: 768px){\n" +
//                "            .navbar-expand-md{-ms-flex-flow:row nowrap;flex-flow:row nowrap;-ms-flex-pack:start;justify-content:flex-start;}\n" +
//                "            .navbar-expand-md .navbar-collapse{display:-ms-flexbox!important;display:flex!important;-ms-flex-preferred-size:auto;flex-basis:auto;}\n" +
//                "        }\n" +
//                "        .navbar-dark .navbar-brand{color:#fff;}\n" +
//                "        .navbar-dark .navbar-brand:hover,.navbar-dark .navbar-brand:focus{color:#fff;}\n" +
//                "        .card{position:relative;display:-ms-flexbox;display:flex;-ms-flex-direction:column;flex-direction:column;min-width:0;word-wrap:break-word;background-color:#fff;background-clip:border-box;border:1px solid rgba(0, 0, 0, 0.125);border-radius:0.25rem;}\n" +
//                "        .card-body{-ms-flex:1 1 auto;flex:1 1 auto;min-height:1px;padding:1.25rem;}\n" +
//                "        .card-title{margin-bottom:0.75rem;}\n" +
//                "        .card-subtitle{margin-top:-0.375rem;margin-bottom:0;}\n" +
//                "        .card-text:last-child{margin-bottom:0;}\n" +
//                "        .card-header{padding:0.75rem 1.25rem;margin-bottom:0;background-color:rgba(0, 0, 0, 0.03);border-bottom:1px solid rgba(0, 0, 0, 0.125);}\n" +
//                "        .card-header:first-child{border-radius:calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0;}\n" +
//                "        .card-deck .card{margin-bottom:15px;}\n" +
//                "        @media (min-width: 576px){\n" +
//                "            .card-deck{display:-ms-flexbox;display:flex;-ms-flex-flow:row wrap;flex-flow:row wrap;margin-right:-15px;margin-left:-15px;}\n" +
//                "            .card-deck .card{-ms-flex:1 0 0%;flex:1 0 0%;margin-right:15px;margin-bottom:0;margin-left:15px;}\n" +
//                "        }\n" +
//                "        .jumbotron{padding:2rem 1rem;margin-bottom:2rem;background-color:#e9ecef;border-radius:0.3rem;}\n" +
//                "        @media (min-width: 576px){\n" +
//                "            .jumbotron{padding:4rem 2rem;}\n" +
//                "        }\n" +
//                "        .justify-content-start{-ms-flex-pack:start!important;justify-content:flex-start!important;}\n" +
//                "        .fixed-top{position:fixed;top:0;right:0;left:0;z-index:1030;}\n" +
//                "        .shadow-sm{box-shadow:0 0.125rem 0.25rem rgba(0, 0, 0, 0.075)!important;}\n" +
//                "        .my-0{margin-top:0!important;}\n" +
//                "        .my-0{margin-bottom:0!important;}\n" +
//                "        .mb-2{margin-bottom:0.5rem!important;}\n" +
//                "        .mb-3{margin-bottom:1rem!important;}\n" +
//                "        .mb-4{margin-bottom:1.5rem!important;}\n" +
//                "        .text-left{text-align:left!important;}\n" +
//                "        .text-center{text-align:center!important;}\n" +
//                "        .font-weight-normal{font-weight:400!important;}\n" +
//                "        .text-muted{color:#6c757d!important;}\n" +
//                "        @media print{\n" +
//                "            *,*::before,*::after{text-shadow:none!important;box-shadow:none!important;}\n" +
//                "            a:not(.btn){text-decoration:underline;}\n" +
//                "            img{page-break-inside:avoid;}\n" +
//                "            p{orphans:3;widows:3;}\n" +
//                "            body{min-width:992px!important;}\n" +
//                "            .container{min-width:992px!important;}\n" +
//                "            .navbar{display:none;}\n" +
//                "        }\n" +
//                "    </style>\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "</head>\n" +
//                "<body style='min-height: 75rem;padding-top: 4.5rem;box-sizing: border-box;margin: 0;font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;;font-size: 1rem;font-weight: 400;line-height: 1.5;color: #212529;text-align: left;background-color: #fff;'>\n" +
//                "\n" +
//                "<div class='navbar navbar-expand-md navbar-dark fixed-top ' style='background-color: #97ddee !important;box-sizing: border-box;'>\n" +
//                "  <span class='navbar-brand' style='box-sizing: border-box;'>\n" +
//                "    <img class='sp-default-logo' src='https://app1.goniometer-exoglove.me/img/Xtreme%20Sport%20Channel%20Logo.gif' alt='Televiaducto' width='160' style='box-sizing: border-box;vertical-align: middle;border-style: none;'>\n" +
//                "  </span>\n" +
//                "\n" +
//                "    <div class='collapse navbar-collapse' id='navbarCollapse' style='box-sizing: border-box;'>\n" +
//                "\n" +
//                "    </div>\n" +
//                "</div>\n" +
//                "<br style='box-sizing: border-box;'>\n" +
//                "<div role='main' class='container' style='box-sizing: border-box;max-width: 960px;width: 100%;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;'>\n" +
//                "\n" +
//                "\n" +
//                "    <div class='jumbotron' style='box-sizing: border-box;'>\n" +
//                "\n" +
//                "        <div class='card' style='box-sizing: border-box;'>\n" +
//                "            <div class='card-body' style='box-sizing: border-box;'>\n" +
//                "                <h4 class='card-title' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.5rem;'>Alert</h4>\n" +
//                "                <h5 class='card-subtitle mb-2 text-muted' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>Error en el servidor</h5>\n" +
//                "\n" +
//                "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
//                "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
//                "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>Severity: </h5>\n" +
//                "                    </div>\n" +
//                "                    <div class='col' style='box-sizing: border-box;'>\n" +
//                "                        <p class='card-text lead btn-danger' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>Critical</p>\n" +
//                "                    </div>\n" +
//                "                </div>\n" +
//                "                <div class='row text-left' style='box-sizing: border-box;'>\n" +
//                "                    <div class='col-4' style='box-sizing: border-box;'>\n" +
//                "                        <h5 class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.25rem;'>Timestamp: </h5>\n" +
//                "                    </div>\n" +
//                "                    <div class='col' style='box-sizing: border-box;'>\n" +
//                "                        <p class='card-text lead' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;font-size: 1.25rem;font-weight: 300;'>2020-07-24 12:46:01</p>\n" +
//                "                    </div>\n" +
//                "                </div>\n" +
//                "\n" +
//                "\n" +
//                "            </div>\n" +
//                "        </div>\n" +
//                "        <br style='box-sizing: border-box;'>\n" +
//                "        <div class='' style='box-sizing: border-box;'>\n" +
//                "            <div class='card-deck mb-3 text-center' style='box-sizing: border-box;'>\n" +
//                "\n" +
//                "                <div class='card mb-4 shadow-sm' style='box-sizing: border-box;min-width: 220px;'>\n" +
//                "                    <div class='card-header' style='box-sizing: border-box;'>\n" +
//                "                        <h4 class='my-0 font-weight-normal text-left' style='box-sizing: border-box;margin-top: 0;margin-bottom: 0.5rem;font-weight: 500;line-height: 1.2;font-size: 1.5rem;'>Faults <a style='box-sizing: border-box;color: inherit;text-decoration: none;background-color: transparent;'>#1</a></h4>\n" +
//                "                    </div>\n" +
//                "                    <div class='card-body ' style='box-sizing: border-box;'>\n" +
//                "                        <div class='justify-content-start' style='box-sizing: border-box;'>\n" +
//                "\n" +
//                "                            <div class='row text-left' style='box-sizing: border-box;'>\n" +
//                "                                <div class='col' style='box-sizing: border-box;'>\n" +
//                "                                    <p class='card-text' style='box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;'>5688;</p>\n" +
//                "                                </div>\n" +
//                "                            </div>\n" +
//                "                        </div>\n" +
//                "\n" +
//                "\n" +
//                "                    </div>\n" +
//                "\n" +
//                "                </div>\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "            </div>\n" +
//                "        </div>\n" +
//                "\n" +
//                "\n" +
//                "    </div>\n" +
//                "</div>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>","prueba");

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
