"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
// import 'rxjs/Rx';
/**
 * @author Seán Vaeth
 *
 * This component requires some documentation
 * Datasync is bootstrapped with the app. This means it will run on ng serve before components
 * that use it are initialized. It also has the benefit of being singleton across the app,
 * as opposed to providers being prototyped.
 *
 * The purpose of the datasync service is to synchronize all data across the app for all sessions
 * with the data read in from the database containing Salesforce data. DSS will quietly run in the
 * background of any component dependent on server side data and update accordingly.
 * DSS also ensures only one thread is used to hit the server side in background methods.
 * All other requests are prompted by the logged user with appropriate permissions
 *
 * This allows the application to run faster using cached data pulled in from the server at set intervals
 *
 *
 *
 *
 * Reviewed by Max
 * Not currently being used, but this is a good idea
 * Note made 6/6/2018
 */
var ASSOC_TIMEOUT = 30000;
var BATCH_TIMEOUT = 30000;
var CLIENT_TIMEOUT = 30000;
var CURRI_TIMEOUT = 30000;
var MARKET_TIMEOUT = 30000;
var ASSOC_INT = 2000;
var BATCH_INT = 2000;
var CLIENT_INT = 2000;
var CURRI_INT = 2000;
var MARKET_INT = 2000;
var DataSyncService = /** @class */ (function () {
    // inject RequestService for handling requests to the server
    function DataSyncService(rs, bs, cs, as, ss) {
        this.rs = rs;
        this.bs = bs;
        this.cs = cs;
        this.as = as;
        this.ss = ss;
        // fetch data on initialization
        this.fetchData();
    }
    DataSyncService.prototype.checkFetchData = function () {
    };
    /**
     * @function fetchData
     *
     * Fetch data from server
     * this method populates our caches
     * and begins the data monitoring observables
     */
    DataSyncService.prototype.fetchData = function () {
        // get data on load
        this.fetchAssociateStorage();
        // this.fetchBatchStorageSortedById();
        // this.fetchBatchStorageSortedByDate();
        this.fetchClientStorage();
        this.fetchCurriculumStorage();
        this.fetchMarketingStorage();
    };
    /**
     *  getters and setters
     *  Getters: gets data from server using http requests then subscribes to storage updates
     *  Setters: stores data to storage units then subscribes on more data input
     *            notifies observers of change to stored data
     * */
    //
    DataSyncService.prototype.setAssociateStorage = function (data) {
        var newStorage = this.associateStorage = data;
        // this.associateStorage.next(newSt orage);
    };
    DataSyncService.prototype.fetchAssociateStorage = function () {
        this.as.getAllAssociates().subscribe(function (data) {
            // this.setAssociateStorage(data);
        });
    };
    DataSyncService.prototype.setClientStorage = function (data) {
        var newStorage = this.clientStorage = data;
        // this.clientStorage.next(newStorage);
    };
    DataSyncService.prototype.fetchClientStorage = function () {
        this.cs.getAllClients().subscribe(function (data) {
            // this.setClientStorage(data);
        });
    };
    DataSyncService.prototype.setBatchStorageSortedById = function (data) {
        var newStorage = this.batchStorageById = data;
        // this.batchStorageById.next(newStorage);
    };
    // public fetchBatchStorageSortedById() {
    //   this.bs.getBatchesSortedById().subscribe(data => {
    //     // this.setBatchStorageSortedById(data);
    //   });
    // }
    DataSyncService.prototype.setBatchStorageSortedByDate = function (data) {
        var newStorage = this.batchStorageByDate = data;
        // this.batchStorageByDate.next(newStorage);
    };
    // public fetchBatchStorageSortedByDate() {
    //   this.bs.getBatchesSortedByDate().subscribe(data => {
    //     // this.setBatchStorageSortedByDate(data);
    //   });
    // }
    DataSyncService.prototype.setCurriculumStorage = function (data) {
        var newStorage = this.curriculumStorage = data;
        // this.curriculumStorage.next(newStorage);
    };
    DataSyncService.prototype.fetchCurriculumStorage = function () {
        this.ss.getAllCurricula().subscribe(function (data) {
            // this.setCurriculumStorage(data);
        });
    };
    DataSyncService.prototype.setMarketingStorage = function (data) {
        this.marketingStorage = data;
        this.marketingStorage.subscribe(function () {
        });
    };
    DataSyncService.prototype.fetchMarketingStorage = function () {
        this.rs.getStatuses().subscribe(function (data) {
            // this.setMarketingStorage(data);
        });
    };
    DataSyncService = __decorate([
        core_1.Injectable()
    ], DataSyncService);
    return DataSyncService;
}());
exports.DataSyncService = DataSyncService;
