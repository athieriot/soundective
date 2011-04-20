require.def("views/playerView", ["order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min",
                           "order!external/ICanHaz-0.9.min"], function() {

    var PlayerView = Backbone.View.extend({
        initialize: function (args) {
            _.bindAll(this, 'changeTitle');

            this.model.bind('change:title', this.changeTitle);
        },

        events: {
            'click .title': 'handleTitleClick'
        },

        render: function () {
            // "ich" is ICanHaz.js magic
            this.el = ich.songs({song: this.model.toJSON()});

            return this;
        },

        changeTitle: function () {
            this.$('.title').text(this.model.get('title'));
        },

        handleTitleClick: function () {
            alert('you clicked the title: ' + this.model.get('title'));
        }
    });

    return PlayerView;
});