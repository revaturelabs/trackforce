"use strict";
/** @Author Princewill Ibe */
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var environment_1 = require("../../../environments/environment");
var BatchService = /** @class */ (function () {
    function BatchService(http) {
        this.http = http;
        this.baseURL = environment_1.environment.url + 'TrackForce/batches';
    }
    /**
     *  This gets all of the batches, every single one
     */
    BatchService.prototype.getAllBatches = function () {
        var url = this.baseURL;
        return this.http.get(url);
    };
    /*
      Get batches with dates
    */
    BatchService.prototype.getBatchesWithinDates = function (startDate, endDate) {
        var url = this.baseURL +
            '/withindates' +
            ("/?start=" + startDate.getTime() + "&end=" + endDate.getTime());
        return this.http.get(url);
    };
    /**
     * Given start and end date, return the batches that started and completed
     * within the time range
     *
     * @param {Date} startDate - needs to be in long time
     * @param {Date} endDate - needs to be in long time
     * @returns {Observable<Batch[]>}
     */
    BatchService.prototype.getBatchesByDate = function (startDate, endDate) {
        var url = this.baseURL + ("/?start=" + startDate.getTime() + "&?end=" + endDate.getTime());
        return this.http.get(url);
    };
    /**
     * This method will get a list of associates in the batch with given id
     * @param {number} id - the id of the batch you want the assciates for
     * @returns {Observable<Associate[]>}
     */
    BatchService.prototype.getAssociatesForBatch = function (id) {
        var url = this.baseURL + '/' + id + '/associates';
        return this.http.get(url);
    };
    /*
      1806_Andrew_H
      This method sends a start and end date along with the coursename I.E. Java,PEGA.
      A Json object array is received and returned
      See 1806 iteration 6 - Team 4 Predictions - Request Response Doc - #1
    */
    BatchService.prototype.getBatchDetails = function (startDate, endDate, CourseName) {
        var url = this.baseURL +
            ("/details?start=" + startDate.getTime() + "&end=" + endDate.getTime() + "&courseName=" + CourseName);
        return this.http.get(url);
    };
    BatchService.prototype.getAssociateCountByCurriculum = function (startDate, endDate, CourseName) {
        return this.http.get(this.baseURL +
            ("/countby?start=" + startDate.getTime() + "&end=" + endDate.getTime() + "&courseName=" + CourseName));
    };
    /**
     *1806_Kevin_C
     * @param id Id of a batch
     * @returns {Batch} Batch with that id
     */
    BatchService.prototype.getBatchDetailsById = function (id) {
        var url = this.baseURL + '/batch/' + id;
        return this.http.get(url);
    };
    BatchService = __decorate([
        core_1.Injectable()
    ], BatchService);
    return BatchService;
}());
exports.BatchService = BatchService;
