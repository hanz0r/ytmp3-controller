/**
 * Created by brock on 9/11/2015.
 */

var app = (function (youtube, data) {

    $("#search_form").submit(function (event) {
        /*
         * Look up the video on Youtube
         */
        youtube.search_videos($("#query").val(), function (search_results) {
            download_single(search_results.items, 0);
        });

        $("#query").val("");
        event.preventDefault();
    });

    download_single = function (items, index) {
        $.ajax({
            url: "/lookup",
            method: "GET",
            data: {
                video_id: items[index].id.videoId
            }
        }).done(function (url) {
            swal({
                title: '',
                text: "Do you want to download '" + items[index].snippet.title + "'?",
                type: "info",
                showCancelButton: true,
                confirmButtonText: "Yes!",
                cancelButtonText: "No!",
            }, function (isConfirm) {
                if (isConfirm) {
                    $.fileDownload(url, {
                        successCallback: function (url) {
                            alert('You just got a file download dialog or ribbon for this URL :' + url);
                        },
                        failCallback: function (error) {
                            console.log(error);
                        }
                    })
                }
            });
        }).fail(function () {
            download_single(items, index + 1);
        });
    }

})(youtube, data);