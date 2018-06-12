/**
 * Object to hold data regarding an associate.
 * @author Alex, Xavier
 */
import {StatusInfo} from "./status-info.model";

export class Associate {
  id: number;
  firstName: string;
  lastName: string;
  // marketing status id
  msid: number;
  marketingStatus: string;
  // Kirk: Verified is not in AssociateInfo.java
  verified: string;
  // Client id
  clid: number;
  client: string;
  // Batch id ????
  bid: number;
  // Curriculum id
  curid: number;
  curriculumName: string;
  // end client id
  ecid: number;
  // Kirk: Within ApplicationInfo.java, endClientName is endClient;
  endClientName: string;
  batchName: string;
  // I don't think this field is used at all through out the program
  batchId: string;
  // Kirk: Within ApplicationInfo.java, clientStartDate maybe correlated to startDate;
  clientStartDate: Date;
  // approved by the trainer ????
  isApproved: number;
}
