"use strict";
exports.__esModule = true;
/**
 * Model holding data relating to a client
 * @author Alex, Xavier
 */
var Client = /** @class */ (function () {
    function Client(id, name, placement, associate, interview, stats) {
        this.id = id;
        this.name = name;
        this.placement = placement;
        this.associate = associate;
        this.stats = stats;
        this.interview = interview;
    }
    return Client;
}());
exports.Client = Client;
