/**
 * Object to hold data regarding an associate.
 * @author Alex, Xavier
 */
import { StatusInfo } from "./status-info.model";
import { Batch } from "./batch.model";
import { User } from "./user.model";
import { Client } from "./client.model";
import { MarketingStatus } from "./marketing-status.model";
import { EndClient } from "./end-client.model";
import { Interview } from "./interview.model";
import { Placement } from "./placement.model";

export class Associate {
  id: number;
  firstName: string;
  lastName: string;
  batch: Batch;
  user: User;
  marketingStatus: MarketingStatus;
  client: Client;
  endClient: EndClient;
  interview: Interview;
  placement: Placement;
  //isApproved: number;
  clientStartDate: Date;

  /*These fields are commented out
  because they are not in the Java code
  msid: number; 
  marketingStatus: string;
  // Kirk: Verified is not in AssociateInfo.java
  verified: string;
  // Client id
  clid: number;

  // Batch id ????

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

  // approved by the trainer ???? */

}
