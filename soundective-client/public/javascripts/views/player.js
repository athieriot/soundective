require.def("views/player", ["order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min"], function() {

    var ids = new Array();

    var Player = Backbone.View.extend({

        playerTheme: 'light',
        flashPath: '/public/javascripts/external/swf/jarisplayer.swf',

        initialize: function (args) {
            this.id = ids.length + 1;
            ids.push(this.id);

            _.bindAll(this, "addItem");

            this.model.bind('add', this.addItem);
        },

        render: function (domElement) {
            //Speakker initialization
            $(domElement).speakker({
                identifier: this.id,
                file: (this.model.length == 0 ? null : this.model.toJSON()),
                playlist: true,
                theme: this.playerTheme,
                playerFlashMP4: this.flashPath,
                playerFlashMP3: this.flashPath
            });

            this.instance = projekktor(this.id - 1);

            return this;
        },

        addItem: function(item) {
            this.instance && this.instance.setItem(item.toJSON(), null).setActiveItem('poster');

            return this;
        },
    });

    return Player;
});