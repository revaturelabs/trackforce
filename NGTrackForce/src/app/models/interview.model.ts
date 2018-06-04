import { Associate } from "./associate.model";
import { EndClient } from './end-client.model';


export class Interview {
   id: number;
   associateId: number;
   clientId: number;
   typeId: number;
   dateAssociateIssued: number;
   interviewDate: number;
   jobDescription: string;
   associateFeedback: string;
   clientFeedback: string;
   questions: string;
   was24HRNotice: number;
   flagAlert: number;
}