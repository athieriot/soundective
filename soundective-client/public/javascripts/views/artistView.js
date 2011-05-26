require.def("views/artistView", ["order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min",
                           "order!external/ICanHaz-0.9.min"], function() {


    var ArtistView = Backbone.View.extend({
        initialize: function (args) {
            _.bindAll(this, 'handleArtistClick');
        },

        events: {
            'click .artist': 'handleArtistClick'
        },

        render: function () {
            this.el = ich.artists({artists: this.model.toJSON()})

            return this;
        },

        handleArtistClick: function () {
            console.log('you clicked one artist.');
        }
    });

    return ArtistView;

});