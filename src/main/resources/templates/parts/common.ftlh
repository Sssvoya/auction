<#macro page>
    <#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="en" xmlns="http://www.w3.org/1999/html">
    <head>
        <meta charset="UTF-8">
        <title><@spring.message "common.title"/></title>

        <!--Bootstrap template-->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!--Bootstrap style-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-ratea3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- reCaptcha -->
        <script src="https://www.google.com/recaptcha/api.js"></script>

    </head>
    <body>
    <#include "navbar.ftlh">
    <div class="container my-5">
        <#nested>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
            integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-ratea3/dist/js/bootstrap.min.js"
            integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
            crossorigin="anonymous"></script>

    <!--My scripts -->

    <script type="text/javascript">
        function dateToUTC() {
            let startDate = document.getElementById('date').value;

            document.getElementById('hiddenDate').value = new Date(startDate).toUTCString();
        }

        function hiddenDateIsNow() {
            document.getElementById('hiddenDate').value = new Date().toUTCString();
        }

    </script>

    </body>
    </html>
</#macro>

<#macro scriptPage onloadScript>
    <#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="en" xmlns="http://www.w3.org/1999/html">
    <head>
        <meta charset="UTF-8">
        <title><@spring.message "common.title"/></title>

        <!--Bootstrap template-->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!--Bootstrap style-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-ratea3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- reCaptcha -->
        <script src="https://www.google.com/recaptcha/api.js"></script>

        <!-- WebSocket -->
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script src="/webjars/sockjs-client/sockjs.min.js"></script>
        <script src="/webjars/stomp-websocket/stomp.min.js"></script>
        <script src="/static/js/ws/Price.js"></script>

        <!-- moment.js -->
        <script src="/static/js/libs/moment.min.js"></script>

    </head>
    <body onload=${onloadScript}>
    <#include "navbar.ftlh">
    <div class="container my-5">
        <#nested>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
            integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-ratea3/dist/js/bootstrap.min.js"
            integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
            crossorigin="anonymous"></script>


    <!--My scripts -->

    <script type="text/javascript">
        function dateToUTC() {
            let startDate = document.getElementById('date').value;

            document.getElementById('hiddenDate').value = new Date(startDate).toUTCString();
        }

        function hiddenDateIsNow() {
            document.getElementById('hiddenDate').value = new Date().toUTCString();
        }

    </script>

    </body>
    </html>
</#macro>
