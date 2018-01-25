var comands = [];

$(document).ready(function () {
    var webSocket = new WebSocket('ws://127.0.0.1:8080/messages');

    webSocket.onmessage = function (ev) {
        var response = JSON.parse(ev.data);
        if (response.message === "add") {
            var robotId = response.robot.name + '-robot';
            $('<div>', {
                    class: 'robot',
                    id: robotId
                }
            ).insertBefore('.robot-list .robot:first-child');

            $('<div>', {
                    class: 'robot-name',
                    text: response.robot.name
                }
            ).appendTo('#' + robotId + '');

            for (var i = 0; i < response.robot.methods.length; i++) {

                var command = response.robot.methods[i];

                $('<button>', {
                    type: 'button',
                    class: 'command',
                    text: command,
                    value: command.toString(),

                    on: {
                        click: function (){
                            $.ajax({
                                url : '/api/robot/'+ response.robot.name + '/' + this.value,
                                datatype : 'json',
                                type : "post",
                                contentType : "application/json"
                            });
                        }
                    }
                }).appendTo('#' + robotId + '');

                if (comands.indexOf(response.robot.methods[i]) === -1) {
                    comands.push(response.robot.methods[i]);

                    $('<button>', {
                        type: 'button',
                        class: 'command',
                        text: command,
                        value: command.toString(),
                        on: {
                            click: function (){
                                $.ajax({
                                    url : '/api/robots/commands/' + this.value,
                                    datatype : 'json',
                                    type : "post",
                                    contentType : "application/json"
                                });
                            }
                        }
                    }).appendTo('.commands');

                }
            }

        } else if (response.message === "remove") {
            $('#' + response.robot.name + '-robot').remove();
        } else {
            $('<tr>', {
                append: $('<td>', {
                    text: response.message
                })
            }).appendTo('.logtable table');
        }
    };

    $('#add-form').find('.command').click (function () {

        var type = $("#robot-type").find("option:selected").attr('value');
        var name = $("#robot-name").val();

        $.ajax({
            url : '/api/robots/add/' + type + '/' + name,
            datatype : 'json',
            type : "post",
            contentType : "application/json"
        });
        return false;
    });

});