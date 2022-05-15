let stompClient = null;

function connect() {

    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/timer', function (timerResponse) {
            updateTimer(timerResponse);
        });

        stompClient.subscribe('/topic/rates/' + getLotInAuId(), function (PriceResponse) {
            showPriceResponse(PriceResponse);
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendStatus(status) {
    stompClient.send("/app/Price", {}, JSON.stringify({
        'id': getLotInAuId(),
        'status': status
    }));
}

function showPriceResponse(PriceResponse) {
    let Price = JSON.parse(PriceResponse.body);

    $("#rate").text(Price.rate);
    $("#Username").text(Price.Username);

    let date = moment(Price.date);
    $("#rateDate").text(date.format('YYYY.MM.DD H:mm:ss'));

}

function updateTimer(timerResponse) {

    let backendTime = JSON.parse(timerResponse.body).content;

    let rateDateString = $("#rateDate").html().toString();
    rateDateString = rateDateString
        .replaceAll('.', '-')
        .replace(' ', 'T');

    let rateDate = moment(rateDateString);
    let intervalMillis = $("#interval").html() * 60000; // 1 мин = 60 000 мс
    let endAuctionDate = rateDate + intervalMillis; // предполагаемая дата окончания аукциона (если не будет новых ставок)

    let timeLeft = Number((endAuctionDate - backendTime) / 1000).toFixed(0); // осталось до конца торгов, секунд

    if (timeLeft * 1000 > intervalMillis) { //если до конца аукциона осталось > чем интервал ставки (торги еще не начались)

        let secondsLeft = timeLeft % 60; // 1..59
        let minutesLeft = (timeLeft - secondsLeft - intervalMillis / 1000) / 60 % 60; // 1..59
        let hoursLeft = (timeLeft - minutesLeft * 60 - secondsLeft - intervalMillis / 1000) / 3600 % 60; // 1..23
        if(hoursLeft < 6){
            $("#timerText").text('До начала аукциона: ');
            $("#timer").text(hoursLeft + ':' + minutesLeft + ':' + secondsLeft);
        }else{
            $("#timerText").text('Дата начала аукциона: ');
            $("#timer").text($("#rateDate").html().toString())
        }

    } else { //если до конца аукциона осталось <= чем интервал ставки (торги в процессе)
        $("#timerText").text('До конца аукциона: ');
        $("#timer").text(timeLeft);

        if (timeLeft <= 1) {
            sendStatus('FINISHED');
            location.reload();
        }
    }

}

function getLotInAuId() {
    let url = window.location.pathname;
    let urlParts = url.split('/');
    let idIndex = urlParts.indexOf('LotInAu') + 1;

    return urlParts[idIndex];
}
