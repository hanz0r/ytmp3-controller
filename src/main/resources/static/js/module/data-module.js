/**
 * Created by brock on 9/11/2015.
 */

var data = (function () {

    return {
        get_file_url: function (video_id, success) {
            $.ajax({
                url: "http://localhost:8080/lookup/",
                method: "POST",
                data: {
                    video_id: video_id
                }
            }).done(success);
        }
    }

})();