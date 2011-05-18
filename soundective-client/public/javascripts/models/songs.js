require.def("models/songs", ["order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function() {

    var Song = Backbone.Model.extend({

        url: '/song',

        binarySongUrl: function() {return this.url + '/' + this.get('id') + '.' + this.get('extension');},

        albumImageUrl: function() {return this.url + '/' + this.get('id') + '/album-image';}

    });

    var Songs = Backbone.Collection.extend({
        model: Song,

        url: '/songs'
    });

    return Songs;
});