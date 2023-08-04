let accessToken = "내 accessToken";
$.ajax({
  url: "<https://api.spotify.com/v1/browse/new-releases>",
  type: "GET",
  headers: {
    Authorization: "Bearer " + accessToken,
  },
  success: function (data) {
    console.log(data);
  },
});
