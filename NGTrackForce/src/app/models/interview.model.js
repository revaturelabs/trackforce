"use strict";
exports.__esModule = true;
var Interview = /** @class */ (function () {
    function Interview(associate, client, interviewType, interviewDate, questionGiven, was24HRNotice, dateSalesIssued, dateAssociateIssued, jobDescription, isClientFeedbackVisible, associateFeedback, clientFeedback, isInterviewFlagged, flagReason, endClient, id) {
        this.associate = associate;
        this.client = client;
        this.interviewType = interviewType;
        this.interviewDate = interviewDate;
        this.questionGiven = questionGiven;
        this.was24HRNotice = was24HRNotice;
        this.jobDescription = jobDescription;
        this.dateSalesIssued = dateSalesIssued;
        this.dateAssociateIssued = dateAssociateIssued;
        this.isClientFeedbackVisible = isClientFeedbackVisible;
        this.associateFeedback = associateFeedback;
        this.clientFeedback = clientFeedback;
        this.isInterviewFlagged = isInterviewFlagged;
        this.flagReason = flagReason;
        this.endClient = endClient;
        this.id = id;
    }
    return Interview;
}());
exports.Interview = Interview;
