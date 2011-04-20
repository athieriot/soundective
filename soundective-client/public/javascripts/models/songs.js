require.def("models/songs", ["order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function() {

    var Song = Backbone.Model.extend({

    });

    var Songs = Backbone.Collection.extend({
        model: Song,

        url: '/songs'
    });

    return Songs;
});