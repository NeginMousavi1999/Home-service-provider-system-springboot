var centerLat = document.getElementById("lat");
var centerLng = document.getElementById("lng");

var myMap = new L.Map('map', {
    key: 'web.3KcUAc880N8VwZKtMWIXn7mlcxq4eUsxgFgshuoH',
    maptype: 'dreamy',
    poi: true,
    traffic: false,
    center: [35.699739, 51.338097],
    zoom: 14
});
var marker = L.marker([35.699739, 51.338097]).addTo(myMap);
centerLat.value = "35.699739";
centerLng.value = "51.338097";
//on map binding
myMap.on('click', addMarkerOnMap);

function addMarkerOnMap(e) {
    marker.setLatLng(e.latlng);
    centerLat.value = e.latlng.lat;
    centerLng.value = e.latlng.lng;
    reverse();
}

centerLng.addEventListener("keyup", function (event) {
    if (event.keyCode == 13) {
        marker.setLatLng([centerLat.value, centerLng.value]);
        reverse();
    }
})

//sending request to Reverse API
function reverse() {
    marker.setLatLng([centerLat.value, centerLng.value]);
    var log = document.getElementById("error_massage");
    var url = `https://api.neshan.org/v2/reverse?lat=${centerLat.value}&lng=${centerLng.value}`;
    url = encodeURIComponent(url);

    axios.get(`../api-demo/sepehr/reverse.php?url=${url}`)
        .then(data => {
            myMap.flyTo([centerLat.value, centerLng.value], 16);
            marker.bindPopup(data.data.formatted_address).openPopup();
            // document.getElementById("address").textContent = "آدرس : " + data.data.formatted_address;
        }).catch(err => {
        console.log("error = " + err);
        log.textContent = "Nothing found";
    });
}

$(document).ready(function () {
    $("#add-order-form").submit(function (event) {
        event.preventDefault();
        addOrder();
    });
});


function addOrder() {

    let lat = $("#lat").val();
    let lng = $("#lng").val();
    let subServiceName = $("#subServiceName").val();
    let description = $("#description").val();

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: 'https://api.neshan.org/v4/reverse?lat=' + lat + '&lng=' + lng,
        headers: {'api-key': 'service.CCllm8i911EYDXXyAzW3ely9soE6avZXhlZwM6Xz'},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            let neighbourhood = data.neighbourhood;
            let city = data.city;
            let state = data.state;
            let formatted_address = data.formatted_address;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/customer/doAddOrder',
                data: JSON.stringify({
                    "neighbourhood": neighbourhood,
                    "city": city,
                    "state": state,
                    "formatted_address": formatted_address,
                    "description": description,
                    "subServiceName": subServiceName
                }),
                dataType: 'json',
                cache: false,
                timeout: 600000,
                success: function (response) {
                    console.log(response);
                    $('#succ_massage').html(response);
                },
                error: function (e) {
                    console.log(e)
                    $('#error_massage').html(e);
                }
            });
        },
        error: function (e) {
            console.log(e)
            $('#response').html(e);
        }
    });
}