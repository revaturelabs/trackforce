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
var CurriculumService = /** @class */ (function () {
    function CurriculumService(http) {
        this.http = http;
        this.baseURL = environment_1.environment.url + "TrackForce/skillset";
    }
    /**
     *
     * Gets all of the possible curriculum objects
     * (curricula? curriculums?)
     */
    CurriculumService.prototype.getAllCurricula = function () {
        return this.http.get(this.baseURL);
    };
    /**
     *
     * Get skill set info from the back-end
     *
     * @param {number} statusID - id of the skillset
     */
    CurriculumService.prototype.getSkillsetsForStatusID = function (statusID) {
        return this.http.get((environment_1.environment.url) +
            'TrackForce/skillset/unmapped/' + statusID);
    };
    CurriculumService = __decorate([
        core_1.Injectable()
    ], CurriculumService);
    return CurriculumService;
}());
exports.CurriculumService = CurriculumService;
