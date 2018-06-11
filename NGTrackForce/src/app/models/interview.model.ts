import { Associate } from "./associate.model";
import { EndClient } from './end-client.model';

/**
 * Holds data about interviews
 * Used in the interviews-view, myinterviews-view, and invterview.service
 * It is not clear how the Associate and EndClient imports are used, if at all
 * Note made 6/7/2018
 * @author Amelia
 */
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