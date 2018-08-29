"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var environment_1 = require("../../../environments/environment");
/**
 * Service for retrieving and updating data relating to associates.
 * @author Alex, Xavier
 */
var AssociateService = /** @class */ (function () {
    function AssociateService(http) {
        this.http = http;
        this.baseURL = environment_1.environment.url + 'TrackForce/associates';
        this.nassURL = this.baseURL + '/nass';
    }
    /**
     *
     * Gets all of the associates
     */
    AssociateService.prototype.getAllAssociates = function () {
        var url = this.baseURL + '/allAssociates';
        return this.http.get(url);
    };
    /*
      gets initial associates loaded
    */
    AssociateService.prototype.getNAssociates = function () {
        return this.http.get(this.nassURL);
    };
    /**
     * get the count of the associates to display in the pie charts on the home page
     */
    AssociateService.prototype.getCountAssociates = function () {
        var url = this.baseURL + '/countAssociates';
        return this.http.get(url);
    };
    /**
     *
     * Get specific associate by user id
     * @param id - the user id of the user object of an associate to retrieve
     */
    AssociateService.prototype.getAssociate = function (id) {
        var url = this.baseURL + '/' + id;
        return this.http.get(url);
    };
    /**
     *
     * Get specific associate by associate id
     * @param id - the user id of the user object of an associate to retrieve
     */
    AssociateService.prototype.getByAssociateId = function (id) {
        var url = this.baseURL + '/associates/' + id;
        return this.http.get(url);
    };
    /**
     *
     * Update the given associate's status/client
     * @param ids - list of associate ids of associates to be updated
     * @param marketingStatusId - the marketing status these associates will be updated to
     * @param clientId - the client id that the associates will be mapped to
     */
    AssociateService.prototype.updateAssociates = function (ids, verification, marketingStatusId, clientId) {
        var url = this.baseURL + '?marketingStatusId=' + marketingStatusId + '&clientId=' + clientId + '&verification=' + verification;
        return this.http.put(url, ids);
    };
    /**
     *
     * This method updates the associate in the database
     * @param associate - the associate object with the updated values
     */
    AssociateService.prototype.updateAssociate = function (associate) {
        var url = this.baseURL + '/' + associate.id;
        return this.http.put(url, associate);
    };
    AssociateService.prototype.getAssociatesByStatus = function (statusId) {
        return this.http.get(this.baseURL + '/mapped/' + statusId);
    };
    AssociateService.prototype.approveAssociate = function (associateID) {
        var url = this.baseURL + '/' + associateID + '/approve';
        return this.http.put(url, associateID);
    };
    AssociateService.prototype.getUndeployedAssociates = function (mappedOrUnmapped) {
        var url = this.baseURL + '/undeployed/' + mappedOrUnmapped;
        return this.http.get(url);
    };
    AssociateService = __decorate([
        core_1.Injectable()
    ], AssociateService);
    return AssociateService;
}());
exports.AssociateService = AssociateService;
