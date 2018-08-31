"use strict";
exports.__esModule = true;
/**
 * Model containing information about the current user
 * @author Leng
 *
 * This will need to change to reflect changes being made to the DB
 * (If changes are made to the DB)
 * Note made 6/7/2018
 */
var User = /** @class */ (function () {
    function User(newUsername, newPassword, newRole, newIsApproved, newId, newToken) {
        this.username = newUsername;
        this.password = newPassword;
        this.role = newRole;
        this.isApproved = newIsApproved;
        this.id = newId;
        this.token = newToken;
    }
    return User;
}());
exports.User = User;
