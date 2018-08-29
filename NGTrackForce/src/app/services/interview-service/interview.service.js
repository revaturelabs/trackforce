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
var InterviewService = /** @class */ (function () {
    function InterviewService(http) {
        this.http = http;
        this.baseURL = environment_1.environment.url + 'TrackForce/interviews';
    }
    /**
     *
     * Get all of the interviews, every single one
     */
    InterviewService.prototype.getAllInterviews = function () {
        return this.http.get(this.baseURL);
    };
    /**
     *
     * This method will create a new interview for an associate
     * @param interview - the new interview object
     * @param id - associate id
     *
     */
    InterviewService.prototype.createInterview = function (interview, id) {
        var url = this.baseURL + '/' + id;
        return this.http.post(url, interview);
    };
    /**
     *
     * This method gets all the interviews for a specific associate
     * Reviewed by Max
     * @since 6.18.06.08
     *
     * @param id - this is the associate's id
     */
    InterviewService.prototype.getInterviewsForAssociate = function (id) {
        var url = this.baseURL + '/' + id;
        return this.http.get(url);
    };
    InterviewService.prototype.getInterviewById = function (id) {
        var url = this.baseURL + "/getInterviewById/" + id;
        return this.http.get(url);
    };
    /**
     *
     * This method updates an existing interview
     * Reviewed by Max
     * @since 6.18.06.08
     *
     * @param interview - this is the updated interview object
     * @param id - this is the id of the associate
     */
    InterviewService.prototype.updateInterview = function (interview) {
        var url = this.baseURL + "/" + interview.id;
        return this.http.put(url, interview);
    };
    InterviewService = __decorate([
        core_1.Injectable()
    ], InterviewService);
    return InterviewService;
}());
exports.InterviewService = InterviewService;
