import {Associate} from "./associate.model";
import {EndClient} from './end-client.model';


export class Interview {
  // Kirk: Both id and associateId is not used anywhere.
  //       One can be removed.
  id: number;
  associateId: number;
  // Kirk: Within InterviewInfo.java, there is tfAssociate variable as a AssociateInfo object.
  associate: Associate;
  // Kirk: InterviewInfo.java has tfClientName. So adding clientName variable.
  //       Additionally, clientId is not in InterviewInfo.java.
  clientName: string;
  clientId: number;
  typeId: number;
  interviewDate: number;
  interviewFeedback: string;
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
