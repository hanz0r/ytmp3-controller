/**
 * Created by brock on 9/11/2015.
 */

var youtube = (function () {

    // The Youtube API key
    var api_key = "AIzaSyAxMhTfY2RnVAcOvVo4vI1lEGlQuyedd6I";

    return {
        search_videos: function(query, success) {
            $.ajax({
                url: "https://www.googleapis.com/youtube/v3/search?key=" + api_key + "&part=snippet,id&q=" + encodeURIComponent(query),
                method: "GET"
            }).done(success);
        },

        request: function(url, success, data) {
            $.ajax({
                url: "http://localhost:8080/lookup",
                type: "POST",
                data: data,
            }).done(success)
        }
    };

})();