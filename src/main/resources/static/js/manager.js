$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

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
        data: {
                "fromDate": fromDate,
                "toDate": toDate,
                "status": status,
                "serviceName": serviceName,
                "subServiceName": subServiceName
        },
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

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