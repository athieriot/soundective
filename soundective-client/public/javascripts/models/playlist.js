require.def("models/playlist", ["order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function() {

    var PlaylistItem = Backbone.Model.extend({
    });

    var Playlist = Backbone.Collection.extend({
        model: PlaylistItem,

        addSong: function(song) {
            this.add({
                id: song.get('id'),
                0: {
                    src: song.binarySongUrl(),
                },
                config: {
                    title: song.get('title'),
                    poster: song.albumImageUrl()
                }
            });
        }
    });

    return Playlist;
});