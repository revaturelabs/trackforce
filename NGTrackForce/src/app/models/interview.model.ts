import { Associate } from './associate.model';
import { EndClient } from './end-client.model';
import { Client } from './client.model';
import { InterviewType } from './interview-type';

export class Interview {
  id: number;
  associate: Associate;
  client: Client;
  endClient: EndClient;
  interviewType: InterviewType;
  interviewDate: Date;
  questionGiven: string;
  associateFeedback: string;
  clientFeedback: string;
  jobDescription: string;
  dateSalesIssued: number;
  dateAssociateIssued: number;
  isInterviewFlagged: number;
  flagReason: string;
  isClientFeedbackVisible: number;
  was24HRNotice: number;

  constructor(
    associate: Associate,
    client: Client,
    interviewType: InterviewType,
    interviewDate: Date,
    questionGiven: string,
    was24HRNotice: number,
    dateSalesIssued: number,
    dateAssociateIssued: number,
    jobDescription?: string,
    isClientFeedbackVisible?: number,
    associateFeedback?: string,
    clientFeedback?: string,
    isInterviewFlagged?: number,
    flagReason?: string,
    endClient?: EndClient,
    id?: number,
  ) {
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
}
