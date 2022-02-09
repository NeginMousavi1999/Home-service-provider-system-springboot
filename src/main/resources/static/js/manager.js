$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
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
                    "                        <td>\n" + order.customer.email +
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


            /*            let json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                            + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";*/

            $('#response-body').html(response);

            /*console.log("SUCCESS : ", data);*/
            $("#btn-search").prop("disabled", false);

            $("#response").show();

        },
        error: function (e) {

            let json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
            console.log(this.data);

        }
    });

}