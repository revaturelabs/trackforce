import {Associate} from "./associate.model";
import {EndClient} from './end-client.model';
import { Client } from "./client.model";
import { InterviewType } from "./interview-type";


export class Interview {
  // Kirk: Both id and associateId is not used anywhere.
  //       One can be removed.
  id: number;
  //associateId: number;
  // Kirk: Within InterviewInfo.java, there is tfAssociate variable as a AssociateInfo object.
  associate: Associate;
  client : Client;
  endClient : EndClient;
  // Kirk: InterviewInfo.java has tfClientName. So adding clientName variable.
  //       Additionally, clientId is not in InterviewInfo.java.
  // Cameron: commented out because it's not in the Java entity
  // clientName: string;
  // clientId: number;

  interviewType: InterviewType;
  interviewDate: Date;
  //interviewFeedback: string; Cameron: not in Java entity
  questionGiven: string;
  associateFeedback: string;
  clientFeedback: string;
  jobDescription: string;
  dateSalesIssued: number;
  dateAssociateIssued: number;
  isInterviewFlagged: number;
  flagReason: string;
  isClientFeedbackVisible: number;

  // Kirk: The three following is not included within InterviewInfo.java.
  questions: string;
  was24HRNotice: number;
  flagAlert: number;
}
