"use strict";
exports.__esModule = true;
/**
 * Model information for trainer user.
 * @author Kirk
 * @since 6.18.6.12
 */
var Trainer = /** @class */ (function () {
    function Trainer(firstName, lastName, user, id, primary, coTrainer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.id = id;
        this.primary = primary;
        this.coTrainer = coTrainer;
    }
    return Trainer;
}());
exports.Trainer = Trainer;
