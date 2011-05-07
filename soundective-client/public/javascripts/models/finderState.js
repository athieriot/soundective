require.def("models/finderState", ["order!external/underscore-1.1.6.min", "order!external/backbone-0.3.3.min"], function() {

    var FinderState = Backbone.Model.extend({

        url: '/finderState'
    });

    return FinderState;

});