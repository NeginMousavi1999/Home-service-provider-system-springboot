$(document).ready(function () {

    $("#search-form").submit(function (event) {

        event.preventDefault();

        $("#response-body").text("");
        show_orders();

    });

});

function show_orders() {

    let fromDate = $("#fromDate").val();
    let toDate = $("#toDate").val();
    let status = $("#status").val();
    let serviceName = $("#serviceName").val();
    let subServiceName = $("#subServiceName").val();


    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin/get_by_conditions",
        data: JSON.stringify({
            "fromDate": fromDate,
            "toDate": toDate,
            "status": status,
            "serviceName": serviceName,
            "subServiceName": subServiceName
        }),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            let response;
            for (let i = 0; i < data.length; i++) {
                let order = data[i];
                let expert = order.expert;
                let expertEmail;
                if (expert == null) {
                    expertEmail = "Unknown";
                } else {
                    expertEmail = expert.email;
                }
                let toBeDoneDate = order.toBeDoneDate;
                if (toBeDoneDate == null) {
                    toBeDoneDate = "Unknown";
                }
                let row = "<tr>\n" +
                    "                        <td>\n" + order.orderStatus.toLowerCase() +
                    "                        </td>\n" +
                    "                        <td>\n" + order.registrationDate +
                    "                        </td>\n" +
                    "                        <td>\n" + toBeDoneDate +
                    "                        </td>\n" +
                    "                        <td>\n" + order.address.country + ", "
                    + order.address.city + ", "
                    + order.address.state + ", "
                    + order.address.postalCode +
                    "                        </td>\n" +
                    "                        <td>\n" + expertEmail +
                    "                        </td>\n" +
                    "                        <td>\n" + order.description +
                    "                        </td>\n" +
                    "                        <td>\n" + order.finalPrice +
                    "                        </td>\n" +
                    "                    </tr>";
                response = response + row;
            }

            $('#response-body').html(response);

            $("#btn-search").prop("disabled", false);

            $("#response").show();

        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
            console.log(this.data);
        }
    });
}

function getCustomerServices() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/get_customers_services",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#response-services").text("");
            let response;
            for (let i = 0; i < data.length; i++) {
                let order = data[i];
                let row = "<tr>\n" +
                    "                        <td>\n" + order.subService.service.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.cost +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.description +
                    "                        </td>\n" +
                    "                        <td>\n" + order.registrationDate +
                    "                        </td>\n" +
                    "                        <td>\n" + order.customer.email +
                    "                    </tr>";
                response = response + row;
            }

            $('#response-services').html(response);
            $("#userRole").text("customer");
            $("#response").show();

        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
            console.log(this.data);
        }
    });
}

function getExpertServices() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/get_experts_services",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#response-services").text("");
            let response;
            for (let i = 0; i < data.length; i++) {
                let order = data[i];
                let row = "<tr>\n" +
                    "                        <td>\n" + order.subService.service.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.cost +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.description +
                    "                        </td>\n" +
                    "                        <td>\n" + order.registrationDate +
                    "                        </td>\n" +
                    "                        <td>\n" + order.expert.email +
                    "                    </tr>";
                response = response + row;
            }

            $('#response-services').html(response);
            $("#userRole").text("expert");
            $("#response").show();

        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
            console.log(this.data);
        }
    });
}

function customerService(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/get_customer_services/" + id,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#response-services").text("");
            let response;
            for (let i = 0; i < data.length; i++) {
                let order = data[i];
                let row = "<tr>\n" +
                    "                        <td>\n" + order.subService.service.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.cost +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.description +
                    "                        </td>\n" +
                    "                        <td>\n" + order.registrationDate +
                    "                    </tr>";
                response = response + row;
            }

            $('#response-services').html(response);
            $("#response").show();

        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
            console.log(this.data);
        }
    });
}

function expertService(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/get_expert_services/" + id,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#response-services").text("");
            let response;
            for (let i = 0; i < data.length; i++) {
                let order = data[i];
                let row = "<tr>\n" +
                    "                        <td>\n" + order.subService.service.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.name +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.cost +
                    "                        </td>\n" +
                    "                        <td>\n" + order.subService.description +
                    "                        </td>\n" +
                    "                        <td>\n" + order.registrationDate +
                    "                    </tr>";
                response = response + row;
            }

            $('#response-services').html(response);
            $("#response").show();

        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
            console.log(this.data);
        }
    });
}