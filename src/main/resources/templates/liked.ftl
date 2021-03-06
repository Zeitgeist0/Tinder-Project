<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/assets/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/assets/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <#list profiles>
                                <#items as profile>
                                    <tr>
                                        <td width="10">
                                            <div class="avatar-img">
                                                <img class="img-circle" src="${profile.photo}" />  
                                            </div>

                                        </td>
                                        <td class="align-middle">
                                            ${profile.name}
                                        </td>
                                        <td class="align-middle">
                                          Age: ${profile.age}
                                        </td>
                                        <td class="align-middle">
                                            <a style="text-decoration: none;" href="/messages/${profile.id} ">
                                                <button  class="btn btn-outline-success btn-block"></span> Send a message</button>
                                            </a>
                                        </td>
                                    </tr>

                                </#items>
                            </#list>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>