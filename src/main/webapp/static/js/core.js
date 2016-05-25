$(function () {
    $('#side-menu').metisMenu();

    $.urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        return results[1] || 0;
    }

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function () {
    $(window).bind("load resize", function () {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function () {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});


function initCountryList() {
    var countries;
    $.ajax({
        type: 'get',
        url: "/api/public/country",
        success: function (data) {
            countries = $.map(data, function (obj) {
                return {id: obj.id, text: obj.name};
            })
            $(".country-select").select2({
                data: countries
            });
            if (user_profile.country) {
                $("#country").data('select2').trigger('select', {
                    data: {"id": user_profile.country.id, "text": user_profile.country.name}
                });
            }
        }
    });
}
function initDepartmentList($select, placeholder) {
    $.ajax({
        type: 'get',
        url: "/api/department/all",
        success: function (data) {
            var departments = $.map(data, function (obj) {
                return {id: obj.id, text: obj.name};
            })
            $select.html('').select2({data: [{id: '', text: ''}]});
            $select.select2({
                data: departments,
                placeholder: placeholder
            });
        }
    });
}

function checkJobApplicationExistence(job_post_id) {
    var result;
    $.ajax({
        url: "/api/job_application/check/" + job_post_id,
        type: "GET",
        async: false,
        success: function (data) {
            result = (data != "");
        }
    });
    return result;
}

function logout(data) {
    localStorage.clear();
    $.post('/logout', data)
        .done(function () {
            window.location.href = "/";
        });
}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
