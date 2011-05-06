require.def("views/songView", ["order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min",
                           "order!external/ICanHaz-0.9.min"], function() {


    var SongView = Backbone.View.extend({
        initialize: function (args) {
            _.bindAll(this, 'changeTitle');

            this.model.bind('change:title', this.changeTitle);
        },

        events: {
            'click .title': 'handleTitleClick'
        },

        render: function () {
            this.el = ich.songs({song: this.model.toJSON()})

            return this;
        },


        changeTitle: function () {
            this.$('.title').text(this.model.get('title'));
        },


        handleTitleClick: function () {
            console.log('you clicked the title: ' + this.model.get('title'));
        }
    });

    return SongView;

});