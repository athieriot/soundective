require(["controllers/playerController",
         "order!external/jquery-1.4.2.min",
         "order!external/underscore-1.1.6.min",
         "order!external/backbone-0.3.3.min"], function(PlayerController) {

    $(document).ready(function() {

        new PlayerController;

        Backbone.history.start();
    })
});