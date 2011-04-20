require.def("models/songs", ["models/song", "order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function(Song) {

    var Songs = Backbone.Collection.extend({
        model: Song,

        url: '/songs'
    });

    return Songs;
});