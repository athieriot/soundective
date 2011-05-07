require.def("models/playlist", ["order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function() {

    var PlaylistItem = Backbone.Model.extend({
    });

    var Playlist = Backbone.Collection.extend({
        initialize: function(arg) {
        },

        model: PlaylistItem,

        addSong: function(song) {
            if(this.get(song.get('id')) == undefined) {
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
        },

        addSongs: function(songs) {
            songs.each(_.bind(this.addSong, this));
        }
    });

    return Playlist;
});