<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="css/dashboard.css"/>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/logic.js"></script>

</head>
<body>
    <header>
        <a>Dashboard</a>
    </header>
    <div class="main">
        <div class="robots row">
            <div class="commands">

            </div>
            <div class="robot-list">
                <div class="robot">
                    <form id="add-form" action="" method="post">
                        <label for="robot-type">Robot type</label>
                        <select id="robot-type" name="type">
                            <option value="warrior">Warrior</option>
                            <option value="worker">Worker</option>
                            <option value="seller">Seller</option>
                        </select>
                        <label for="robot-name">Robot name</label>
                        <input id="robot-name" type="text" name="name" value=""/>
                        <button class="command" type="button">Add robot</button>
                    </form>
                </div>
            </div>

        </div>
        <div class="logtable row">
            <table>
                <tr>
                    <th>Messages</th>
                </tr>
            </table>
        </div>

    </div>
</body>
</html>
