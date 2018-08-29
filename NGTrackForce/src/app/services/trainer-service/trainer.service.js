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
var TrainerService = /** @class */ (function () {
    function TrainerService(http) {
        this.http = http;
        this.baseURL = environment_1.environment.url + "TrackForce/trainers/";
    }
    /**
     *
     * Gets a trainer from Java. As a way to prevent infinite recursion,
     * it's coming in without a batch.
     * @param userId
     * @author Curtis H
     * @since 6.18.06.16
     */
    TrainerService.prototype.getTrainer = function (userId) {
        return this.http.get(this.baseURL + userId);
    };
    //
    /**
     *
     * Get all batches associated with a particular trainer
     * @param trainerId
     * @author Curtis H
     * @since 6.18.06.16
     */
    TrainerService.prototype.getTrainerBatches = function (trainerId) {
        return this.http.get(this.baseURL + trainerId + "/batch");
    };
    /**
     * Gets all trainers
     * @author Adam L
     * @since 6.19.06.16
     */
    TrainerService.prototype.getAllTrainers = function () {
        return this.http.get(this.baseURL + "/allTrainers");
    };
    /**
     *
     * Get all batches in which the co-trainer is represented by trainerId
     * @param trainerId
     * @author Curtis H
     * @since 6.18.06.16
     */
    TrainerService.prototype.getCoTrainerBatches = function (trainerId) {
        return this.http.get(this.baseURL + trainerId + "/cotrainerbatch");
    };
    TrainerService = __decorate([
        core_1.Injectable()
    ], TrainerService);
    return TrainerService;
}());
exports.TrainerService = TrainerService;
