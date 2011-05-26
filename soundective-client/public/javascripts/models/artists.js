require.def("models/artists", ["order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function() {

    var Artist = Backbone.Model.extend({

    });

    var Artists = Backbone.Collection.extend({
        model: Artist,

        url: '/artists',

        parse: function(response) {
            return response.map(function(element) {
                return {
                    name: element
                }
            });
        }
    });

    return Artists;
});