function countdown(elementName, minutes, seconds) {
    let element, endTime, hours, min, msLeft, time;

    function twoDigits(n) {
        return (n <= 9 ? "0" + n : n);
    }

    function updateTimer() {
        msLeft = endTime - (+new Date);
        if (msLeft < 1000) {
            element.innerHTML = "Time is up!";
            alert("Your Session has expired. Please try again.");
            window.location = "http://localhost:8080/customer/all_orders";
        } else {
            time = new Date(msLeft);
            hours = time.getUTCHours();
            min = time.getUTCMinutes();
            element.innerHTML = (hours ? hours + ':' + twoDigits(min) : min) + ':' + twoDigits(time.getUTCSeconds());
            setTimeout(updateTimer, time.getUTCMilliseconds() + 500);
        }
    }

    element = document.getElementById(elementName);
    endTime = (+new Date) + 1000 * (60 * minutes + seconds) + 500;
    updateTimer();
}

countdown("ten-countdown", 10, 0);