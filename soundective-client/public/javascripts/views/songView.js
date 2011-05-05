require.def("views/songView", ["order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min",
                           "order!external/ICanHaz-0.9.min"], function() {

    var self = null;

    var SongView = Backbone.View.extend({
        initialize: function (args) {
            self = this;

            _.bindAll(self, 'changeTitle');

            self.model.bind('change:title', self.changeTitle);
        },

        events: {
            'click .title': 'handleTitleClick'
        },

        render: function () {
            self.el = ich.songs({song: self.model.toJSON()})

            return self;
        },


        changeTitle: function () {
            self.$('.title').text(self.model.get('title'));
        },


        handleTitleClick: function () {
            console.log('you clicked the title: ' + self.model.get('title'));
        }
    });

    return SongView;

});