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
  *Service for handling data from the back-end
  *i.e. login data, client data, and etc
  */
var RequestService = /** @class */ (function () {
    /**
      *@param {HttpClient} http
      *Need to create a connection to REST endpoint
      *And initiate Http requests
      */
    function RequestService(http) {
        this.http = http;
        this.host = environment_1.environment.url;
        this.trackPath = this.host + 'TrackForce/';
    }
    RequestService.prototype.populateDB = function () {
        return this.http.get(this.trackPath + 'database/populateDB');
    };
    RequestService.prototype.populateDBSF = function () {
        return this.http.get(this.trackPath + 'database/populateDBSF');
    };
    RequestService.prototype.deleteDB = function () {
        return this.http["delete"](this.trackPath + 'database/deleteFromDB');
    };
    RequestService.prototype.getStatuses = function () {
        return this.http.get(this.trackPath + 'marketing');
    };
    RequestService = __decorate([
        core_1.Injectable()
    ], RequestService);
    return RequestService;
}());
exports.RequestService = RequestService;
