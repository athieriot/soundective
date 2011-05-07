require.def("views/finderStateView", ["models/finderState",
                                      "order!external/jquery-1.4.2.min",
                                      "order!external/underscore-1.1.6.min",
                                      "order!external/backbone-0.3.3.min",
                                      "order!external/ICanHaz-0.9.min"], function(FinderState) {

    var FinderStateView = Backbone.View.extend({
        initialize: function (args) {
            this.model = new FinderState;
        },

        render: function () {
            this.el = ich.finderState(this.model.toJSON())

            return this;
        }
    });

    return FinderStateView;

});