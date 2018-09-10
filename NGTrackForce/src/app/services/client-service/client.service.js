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
 * @author Han Jung
 * @description methods for grabbing client data
 */
var ClientService = /** @class */ (function () {
    function ClientService(http) {
        this.http = http;
        this.baseURL = environment_1.environment.url + 'TrackForce/clients';
        this.mappedClientUrl = environment_1.environment.url + 'TrackForce/clients/mapped/get/';
        this.clientUrl = environment_1.environment.url + 'TrackForce/clients/associates/get/';
        this.fiftyUrl = environment_1.environment.url + 'TrackForce/clients/50';
    }
    /**
     *
     * Get a list of all of the clients
     */
    ClientService.prototype.getAllClients = function () {
        return this.http.get(this.baseURL);
    };
    ClientService.prototype.getFiftyClients = function () {
        return this.http.get(this.fiftyUrl);
    };
    //This method was meant to return all clients with mapped associates.
    //But is currently not used due to incorrect query in the back-end.
    ClientService.prototype.getAllClientsWithAssoc = function () {
        return this.http.get(this.mappedClientUrl);
    };
    //This method returns mapped associate counts for a selected client
    ClientService.prototype.getClientCount = function (clientId) {
        return this.http.get(this.clientUrl + clientId);
    };
    ClientService = __decorate([
        core_1.Injectable()
    ], ClientService);
    return ClientService;
}());
exports.ClientService = ClientService;
