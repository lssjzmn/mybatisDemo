var stompClient = null;

function connect() {
    var socket = new SockJS('/my-sockets');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/serverMsg', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function showMessage(message) {
    // 处理消息在页面的显示
    document.getElementById("webSocket").innerHTML = message.toString();
}

window.onload = function () {
    // 建立websocket连接
    connect();
    // 发送消息按钮事件
    document.getElementById("sendButton").onclick = function () {
        var content = "{'content':'消息内容','receiver':'anoy'}";
        stompClient.send("/app/frontMsg", {}, content);
    };
};